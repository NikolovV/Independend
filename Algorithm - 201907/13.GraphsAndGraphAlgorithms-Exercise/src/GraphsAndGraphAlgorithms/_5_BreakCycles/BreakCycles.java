package GraphsAndGraphAlgorithms._5_BreakCycles;

import java.util.*;
import java.util.stream.Collectors;

public class BreakCycles {
    private static final Map<String, List<String>> graph = new TreeMap<>();

    public static void main(String[] args) {
        readInput();
        List<String> removedEdge = brakeCycles();

        System.out.println(String.format("Edges to remove: %d", removedEdge.size()));
        removedEdge.forEach(System.out::println);

    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String line = in.nextLine();
            int separator = line.indexOf("->");
            String node = line.substring(0, separator).trim();
            String child = line.substring(separator + 3);

            graph.put(node, Arrays.stream(child.split(" ")).sorted().collect(Collectors.toList()));
        }
    }

    private static List<String> brakeCycles() {
        List<String> removedEdge = new ArrayList<>();
        Object[] nodeArray = graph.keySet().toArray();

        for (Object n : nodeArray) {
            String node = (String) n;
            Object[] childArray = graph.get(node).toArray();

            for (Object c : childArray) {
                String child = (String) c;
                removeEdge(node, child);

                if (hasPath(node, child)) {
                    removedEdge.add(String.format("%s - %s", node, child));
                } else {
                    attachEdge(node, child);
                }
            }
        }
        return removedEdge;
    }

    private static void attachEdge(String start, String child) {
        graph.get(start).add(child);
        graph.get(child).add(start);
    }

    private static void removeEdge(String start, String child) {
        graph.get(start).remove(child);
        graph.get(child).remove(start);
    }

    private static boolean hasPath(String start, String end) {
        Queue<String> vertices = new ArrayDeque<>();
        Set<String> visited = new TreeSet<>();

        vertices.offer(start);
        visited.add(start);

        while (vertices.size() > 0) {
            String currentNode = vertices.poll();
            for (String child : graph.get(currentNode)) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    vertices.add(child);
                    if (child.equals(end)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
