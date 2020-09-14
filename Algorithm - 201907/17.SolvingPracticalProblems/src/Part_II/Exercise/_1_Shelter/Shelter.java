package Part_II.Exercise._1_Shelter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Shelter {
    private static double[] times;
    private static double[][] soldiersTimesToShelter;
    private static Point[] soldierLocation;
    private static Point[] shelterLocation;
    private static double timeLimit;
    private static int soldiersCount;
    private static int sheltersCount;
    private static int shelterCapacity;
    private static List<Integer>[] edges;
    private static int[][] graph;
    private static int[] childCounter;
    private static int[] levels;
    private static int endNode;

    public static void main(String[] args) throws IOException {
        readInput();

        setTimes(soldiersCount, sheltersCount);

        endNode = soldiersCount + sheltersCount + 1;
        childCounter = new int[endNode + 1];
        levels = new int[endNode + 1];

        double bestTime = findBestTime();

        System.out.printf("%.6f%n", Math.sqrt(bestTime));
    }

    private static void readInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] battleFieldInfo = in.readLine().split(" ");
        soldiersCount = Integer.parseInt(battleFieldInfo[0]);
        sheltersCount = Integer.parseInt(battleFieldInfo[1]);
        shelterCapacity = Integer.parseInt(battleFieldInfo[2]);

        soldierLocation = new Point[soldiersCount];
        for (int i = 0; i < soldiersCount; i++) {
            String[] soldierLocationData = in.readLine().split(" ");
            int x = Integer.parseInt(soldierLocationData[0]);
            int y = Integer.parseInt(soldierLocationData[1]);
            soldierLocation[i] = new Point(x, y);
        }

        shelterLocation = new Point[sheltersCount];
        for (int i = 0; i < sheltersCount; i++) {
            String[] shelterLocationData = in.readLine().split(" ");
            int x = Integer.parseInt(shelterLocationData[0]);
            int y = Integer.parseInt(shelterLocationData[1]);
            shelterLocation[i] = new Point(x, y);
        }
    }

    private static void setTimes(int soldiersCount, int sheltersCount) {
        soldiersTimesToShelter = new double[soldiersCount][sheltersCount];
        times = new double[soldiersCount * sheltersCount];
        int timeIndex = 0;

        for (int i = 0; i < soldiersCount; i++) {
            for (int j = 0; j < sheltersCount; j++) {
                int distanceX = soldierLocation[i].getX() - shelterLocation[j].getX();
                int distanceY = soldierLocation[i].getY() - shelterLocation[j].getY();

                double time = distanceX * distanceX + distanceY * distanceY;

                times[timeIndex++] = time;
                soldiersTimesToShelter[i][j] = time;
            }
        }
        Arrays.sort(times);
    }

    private static double findBestTime() {
        double bestTime = times[times.length - 1];
        int lowIndex = 0;
        int highIndex = times.length - 1;
        int source = 0;

        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;
            timeLimit = times[midIndex];
            int maxFlow = dinicMaxFlow(source, endNode - 1);
            if (maxFlow < soldiersCount) {
                lowIndex = midIndex + 1;
            } else {
                highIndex = midIndex - 1;
                bestTime = Math.min(timeLimit, bestTime);
            }
        }
        return bestTime;
    }

    private static int dinicMaxFlow(int source, int destination) {
        buildGraph();

        int result = 0;
        while (BFS(source, destination)) {
            Arrays.fill(childCounter, 0);
            int delta;
            do {
                delta = DFS(source, Integer.MAX_VALUE);
                result += delta;
            } while (delta != 0);
        }
        return result;
    }

    private static void buildGraph() {
        graph = new int[endNode + 1][endNode + 1];
        edges = new ArrayList[endNode + 1];
        edges[0] = new ArrayList<>();

        for (int i = 1; i <= soldiersCount; i++) {
            edges[i] = new ArrayList<>();
            edges[0].add(i);
            edges[i].add(0);
            graph[0][i] = 1;
        }

        edges[endNode] = new ArrayList<>();
        for (int i = 1; i <= sheltersCount; i++) {
            edges[soldiersCount + i] = new ArrayList<>();
            edges[soldiersCount + i].add(endNode);
            edges[endNode].add(soldiersCount + i);
            graph[i + soldiersCount][graph.length - 1] = shelterCapacity;
        }

        for (int i = 1; i <= soldiersCount; i++) {
            for (int j = 1; j <= sheltersCount; j++) {
                if (soldiersTimesToShelter[i - 1][j - 1] <= timeLimit) {
                    edges[i].add(soldiersCount + j);
                    edges[soldiersCount + j].add(i);
                    graph[i][j + soldiersCount] = 1;
                }
            }
        }
    }

    private static boolean BFS(int source, int destination) {
        Arrays.fill(levels, -1);
        levels[source] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int i = 0; i < edges[currentNode].size(); i++) {
                int child = edges[currentNode].get(i);
                if (levels[child] < 0 && graph[currentNode][child] > 0) {
                    levels[child] = levels[currentNode] + 1;
                    queue.offer(child);
                }
            }
        }
        return levels[destination] >= 0;
    }

    private static int DFS(int source, int flow) {
        if (source == endNode) {
            return flow;
        }
        for (int i = childCounter[source]; i < edges[source].size(); i++, childCounter[source]++) {
            int child = edges[source].get(i);

            if (graph[source][child] <= 0) {
                continue;
            }

            if (levels[child] == levels[source] + 1) {
                int augmentationPathFlow = DFS(child, Math.min(flow, graph[source][child]));
                if (augmentationPathFlow > 0) {
                    graph[source][child] -= augmentationPathFlow;
                    graph[child][source] += augmentationPathFlow;

                    return augmentationPathFlow;
                }
            }
        }

        return 0;
    }

}

class Point {
    private final int x;
    private final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
