package PartIV_Exercise._3_GraphCycles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class GraphCycles {
    private static boolean isCycleFound = false;
    private static final Map<Integer, Set<Integer>> graph = new TreeMap<>();
    private static final StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < nodesCount; i++) {
            String[] graphData = reader.readLine().split(" -> | ->| ");
            int node = Integer.parseInt(graphData[0]);
            graph.putIfAbsent(node, new TreeSet<>());
            for (int j = 1; j < graphData.length; j++) {
                graph.get(node).add(Integer.parseInt(graphData[j]));
            }
        }

        for (Map.Entry<Integer, Set<Integer>> element : graph.entrySet()) {
            List<Integer> visitedNodes = new ArrayList<>();
            visitedNodes.add(element.getKey());
            findCycles(element.getKey(), 1, visitedNodes);
        }

        if (isCycleFound) {
            System.out.print(result);
        } else {
            System.out.print("No cycles found");
        }
    }

    private static void findCycles(int node, int level, List<Integer> visitedNodes) {
        if (level == 3) {
            int startNode = visitedNodes.get(0);
            Set<Integer> lastNodes = graph.get(visitedNodes.get(visitedNodes.size() - 1));

            if (lastNodes.contains(startNode)) {
                isCycleFound = true;
                result.append(String.format("{%s}%n", visitedNodes.stream().map(Object::toString).collect(Collectors.joining(" -> "))));
            }
            return;
        }

        for (Integer child : graph.get(node)) {
            if (!visitedNodes.contains(child) && child > visitedNodes.get(0)) {
                visitedNodes.add(child);
                findCycles(child, level + 1, visitedNodes);
                visitedNodes.remove(child);
            }
        }
    }
}
