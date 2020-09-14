package AdvancedGraphAlgorithms._1_CableNetwork;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CableNetwork {
    private static final Map<Integer, List<Edge>> graph = new TreeMap<>();
    private static final HashSet<Integer> spanningTreeConnected = new HashSet<>();
    private static int budget;
    private static int usedBudget = 0;

    public static void main(String[] args) {
        readInput();
        primCableConnected();
        System.out.println("Budget used: " + usedBudget);
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);

        budget = Integer.parseInt(in.nextLine().substring(8));
        int nodes = Integer.parseInt(in.nextLine().substring(7));
        int edges = Integer.parseInt(in.nextLine().substring(7));

        IntStream.range(0, nodes).forEach(n -> graph.put(n, new ArrayList<>()));

        for (int i = 0; i < edges; i++) {
            String[] line = in.nextLine().split(" ");
            int firstNode = Integer.parseInt(line[0]);
            int secondNode = Integer.parseInt(line[1]);
            int distance = Integer.parseInt(line[2]);
            boolean isConnected = line.length == 4;

            Edge currentEdge = new Edge(firstNode, secondNode, distance, isConnected);

            graph.get(firstNode).add(currentEdge);
            graph.get(secondNode).add(currentEdge);

            if (currentEdge.isConnected) {
                spanningTreeConnected.add(currentEdge.firstNode);
                spanningTreeConnected.add(currentEdge.secondNode);
            }
        }
    }

    private static void primCableConnected() {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));

        spanningTreeConnected.forEach(n -> queue.addAll(graph.get(n)
                .stream()
                .filter(edge -> !queue.contains(edge))
                .collect(Collectors.toList())));

        while (queue.size() > 0) {
            Edge min = queue.poll();
            int nonTreeNode = -1;
            if (spanningTreeConnected.contains(min.firstNode) && !spanningTreeConnected.contains(min.secondNode)) {
                nonTreeNode = min.secondNode;
            }
            if (!spanningTreeConnected.contains(min.firstNode) && spanningTreeConnected.contains(min.secondNode)) {
                nonTreeNode = min.firstNode;
            }
            if (nonTreeNode == -1) {
                continue;
            }
            if (budget >= min.weight) {
                budget -= min.weight;
                usedBudget += min.weight;
            } else {
                break;
            }

            spanningTreeConnected.add(nonTreeNode);
            queue.addAll(graph.get(nonTreeNode)
                    .stream()
                    .filter(edge -> !queue.contains(edge))
                    .collect(Collectors.toList()));
        }
    }

    private static class Edge {
        public int firstNode;
        public int secondNode;
        public int weight;
        public boolean isConnected;

        public Edge(int firstNode, int secondNode, int weight, boolean isConnected) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.weight = weight;
            this.isConnected = isConnected;
        }

        public int getWeight() {
            return this.weight;
        }
    }
}
