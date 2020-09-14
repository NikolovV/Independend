package AdvancedGraphAlgorithms._2_ModifiedKruskalAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ModifiedKruskalAlgorithm {
    private static Map<Integer, List<Edge>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        String[] pathData = reader.readLine().split(" ");
        int startNode = Integer.parseInt(pathData[1]);
        int endNode = Integer.parseInt(pathData[3]);
        int edgesCount = Integer.parseInt(reader.readLine().split(": ")[1]);

        for (int i = 0; i < edgesCount; i++) {
            String[] edgeParts = reader.readLine().split(" ");
            int from = Integer.parseInt(edgeParts[0]);
            int to = Integer.parseInt(edgeParts[1]);
            int percentage = Integer.parseInt(edgeParts[2]);

            Edge edge = new Edge(from, to, percentage);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(from).add(edge);
            graph.get(to).add(edge);
        }

        double[] percentages = new double[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        int[] prev = new int[graph.size()];

        prev[startNode] = -1;
        for (int i = 0; i < graph.size(); i++) {
            percentages[i] = -1;
        }
        percentages[startNode] = 100;
        visited[startNode] = true;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> percentages[i]));
        queue.offer(startNode);

        while (queue.size() > 0) {
            int min = queue.poll();

            if (percentages[min] == -1) {
                break;
            }

            for (Edge child : graph.get(min)) {
                int otherNode = child.getFirstVertex() == min ? child.getSecondVertex() : child.getFirstVertex();

                if (!visited[otherNode]) {
                    visited[otherNode] = true;
                    queue.add(otherNode);
                }

                double newPercentage = percentages[min] / 100 * child.getPercentage();
                if (percentages[otherNode] < newPercentage) {
                    percentages[otherNode] = newPercentage;
                    prev[otherNode] = min;
                    queue.remove(otherNode);
                    queue.add(otherNode);
                }
            }
        }

        System.out.printf("Most reliable path reliability: %.2f%%%n", percentages[endNode]);

        List<Integer> result = new ArrayList<>();
        int current = endNode;

        while (current != -1) {
            result.add(current);
            current = prev[current];
        }
        Collections.reverse(result);
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" -> ")));
    }

    private static class Edge {
        private int firstVertex;
        private int secondVertex;
        private int percentage;

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
