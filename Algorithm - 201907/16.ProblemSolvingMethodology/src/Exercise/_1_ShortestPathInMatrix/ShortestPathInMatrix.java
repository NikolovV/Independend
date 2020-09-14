package Exercise._1_ShortestPathInMatrix;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathInMatrix {
    private static int rowsCount;
    private static int colsCount;
    private static int[][] matrix;
    private static final Map<Integer, Integer> nodeMap = new TreeMap<>();

    public static void main(String[] args) {
        parseInput();
        int[][] graph = buildGraph();
        int sourceNode = graph[0][0];
        List<Integer> path = dijkstraAlgorithm(graph, sourceNode, graph.length - 1);
        assert path != null;
        printPath(path);
    }

    private static void parseInput() {
        Scanner in = new Scanner(System.in);

        rowsCount = Integer.parseInt(in.nextLine());
        colsCount = Integer.parseInt(in.nextLine());

        matrix = new int[rowsCount][colsCount];
        for (int i = 0; i < rowsCount; i++) {
            matrix[i] = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    private static int[][] buildGraph() {
        int graphLength = rowsCount * colsCount;
        int[][] graph = new int[graphLength][graphLength];

        int graphNode = 0;
        for (int row = 0; row < rowsCount; row++) {
            for (int col = 0; col < colsCount; col++) {
                int currentNodeDistance = matrix[row][col];
                if (col + 1 < colsCount) {
                    graph[graphNode][graphNode + 1] = currentNodeDistance + matrix[row][col + 1];
                    graph[graphNode + 1][graphNode] = currentNodeDistance + matrix[row][col + 1];
                }

                if (row + 1 < rowsCount) {
                    graph[graphNode][graphNode + colsCount] = currentNodeDistance + matrix[row + 1][col];
                    graph[graphNode + colsCount][graphNode] = currentNodeDistance + matrix[row + 1][col];
                }

                nodeMap.put(graphNode, currentNodeDistance);
                graphNode++;
            }
        }
        return graph;
    }

    private static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int[] distance = new int[graph.length];
        int[] prev = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        distance[sourceNode] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distance[i]));
        queue.offer(sourceNode);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                int distanceToChild = graph[vertex][child];
                int totalDistance = distance[vertex] + distanceToChild;

                if (distanceToChild != 0 && distance[child] >= totalDistance) {
                    queue.offer(child);
                    prev[child] = vertex;
                    distance[child] = totalDistance;
                }
            }
        }

        if (prev[destinationNode] == -1) {
            return null;
        }

        return reconstructPath(prev, destinationNode);
    }

    private static LinkedList<Integer> reconstructPath(int[] prev, int destinationNode) {
        LinkedList<Integer> path = new LinkedList<>();

        int current = destinationNode;
        path.addFirst(current);

        while (prev[current] != -1) {
            path.addFirst(prev[current]);
            current = prev[current];
        }

        return path;
    }

    private static void printPath(List<Integer> path) {
        List<Integer> result = new ArrayList<>();
        for (int element : path) {
            result.add(nodeMap.get(element));
        }

        System.out.println("Length: " + result.stream().mapToInt(Integer::intValue).sum());
        System.out.println("Path: " + result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
