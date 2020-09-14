package AdvancedGraphAlgorithms._3_MostReliablePath;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MostReliablePath {
    private static final Map<Integer, List<Edge>> graph = new HashMap<>();
    private static double[] reliablePath;
    private static int startNode;
    private static int endNode;
    private static boolean[] visited;
    private static int[] previous;

    public static void main(String[] args) {
        parseInput();

        List<Integer> path = mostReliablePath();
        System.out.printf("Most reliable path reliability: %.2f%%%n", reliablePath[endNode]);
        System.out.println(path.stream().map(Object::toString).collect(Collectors.joining(" -> ")));
    }

    private static void parseInput() {
        Scanner in = new Scanner(System.in);
        int nodes = Integer.parseInt(in.nextLine().split(": ")[1]);
        IntStream.range(0, nodes).forEach(n -> graph.put(n, new ArrayList<>()));

        String[] pathData = in.nextLine().split(" ");
        startNode = Integer.parseInt(pathData[1]);
        endNode = Integer.parseInt(pathData[3]);
        int edgesCount = Integer.parseInt(in.nextLine().split(": ")[1]);

        for (int i = 0; i < edgesCount; i++) {
            String[] edgeParts = in.nextLine().split(" ");
            int from = Integer.parseInt(edgeParts[0]);
            int to = Integer.parseInt(edgeParts[1]);
            int percentage = Integer.parseInt(edgeParts[2]);

            Edge edge = new Edge(from, to, percentage);

            graph.get(from).add(edge);
            graph.get(to).add(edge);
        }

        reliablePath = new double[graph.size()];
        visited = new boolean[graph.size()];
        previous = new int[graph.size()];

        previous[startNode] = -1;
        for (int i = 0; i < graph.size(); i++) {
            reliablePath[i] = -1;
        }
        reliablePath[startNode] = 100;

    }

    private static List<Integer> mostReliablePath() {
        visited[startNode] = true;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> reliablePath[i]));
        queue.offer(startNode);

        while (queue.size() > 0) {
            int max = queue.poll();

            if (reliablePath[max] == -1) {
                break;
            }

            for (Edge child : graph.get(max)) {
                int otherNode = child.getFirstVertex() == max ? child.getSecondVertex() : child.getFirstVertex();

                if (!visited[otherNode]) {
                    visited[otherNode] = true;
                    queue.add(otherNode);
                }

                double newPercentage = reliablePath[max] / 100 * child.getPercentage();
                if (reliablePath[otherNode] < newPercentage) {
                    reliablePath[otherNode] = newPercentage;
                    previous[otherNode] = max;
                    queue.remove(otherNode);
                    queue.add(otherNode);
                }
            }
        }
        return buildPath();
    }

    private static List<Integer> buildPath() {
        List<Integer> path = new ArrayList<>();
        int current = endNode;

        while (current != -1) {
            path.add(current);
            current = previous[current];
        }
        Collections.reverse(path);
        return path;
    }

    private static class Edge {
        private final int firstVertex;
        private final int secondVertex;
        private final int percentage;

        Edge(int from, int to, int percentage) {
            this.firstVertex = from;
            this.secondVertex = to;
            this.percentage = percentage;
        }

        int getFirstVertex() {
            return this.firstVertex;
        }

        int getSecondVertex() {
            return this.secondVertex;
        }

        int getPercentage() {
            return this.percentage;
        }
    }
}