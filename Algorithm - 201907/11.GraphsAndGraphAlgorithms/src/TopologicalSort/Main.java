package TopologicalSort;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Set<String> visited;
    private static Set<String> cycleNode;
    private static Map<String, List<String>> graph = new LinkedHashMap<>();
    private static Map<String, Integer> predecessorCount;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line.compareTo("") != 0) {
            String[] input = line.replace("\"", "").split("[->,]");

            List<String> child = new ArrayList<>();
            for (int i = 2; i < input.length; i++) {
                child.add(input[i].trim());
            }
            graph.put(input[0].trim(), child);
            line = in.nextLine();
        }

        Collection<String> topSorting = topSort(graph);
        System.out.println("Topological sorting: " + topSorting.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }

    private static void getPredecessorCount(Map<String, List<String>> graph) {
        predecessorCount = new HashMap<>();
        for (String node : graph.keySet()) {
            if (!predecessorCount.containsKey(node)) {
                predecessorCount.put(node, 0);
            }
            for (String child : graph.get(node)) {
                if (!predecessorCount.containsKey(child)) {
                    predecessorCount.put(child, 0);
                }

                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        List<String> result = new ArrayList<>();
        getPredecessorCount(graph);
        while (true) {
            Optional<String> nodeToRemove = predecessorCount.keySet()
                    .stream()
                    .filter(x -> predecessorCount.get(x) == 0)
                    .findFirst();
            if (!nodeToRemove.isPresent()) {
                break;
            }

            result.add(nodeToRemove.get());
            for (String child : graph.get(nodeToRemove.get())) {
                predecessorCount.put(child, predecessorCount.get(child) - 1);
            }
            graph.remove(nodeToRemove.get());
            predecessorCount.remove(nodeToRemove.get());
        }

        if (graph.size() > 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

//    public static Collection<String> topSort(Map<String, List<String>> graph) {
//        Deque<String> result = new ArrayDeque<>();
//        cycleNode = new TreeSet<>();
//        visited = new TreeSet<>();
//
//        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
//            topSortDFS(graph, node.getKey(), result);
//        }
//
//        return new ArrayDeque<>(result);
//    }

    private static void topSortDFS(Map<String, List<String>> graph, String node, Deque<String> result) {
        if (cycleNode.contains(node)) {
            throw new IllegalArgumentException();
        }
        if (!visited.contains(node)) {
            visited.add(node);
            cycleNode.add(node);
            for (String child : graph.get(node)) {
                topSortDFS(graph, child, result);
            }
            cycleNode.remove(node);
            result.addFirst(node);
        }
    }

}
