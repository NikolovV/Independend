package GraphsAndGraphAlgorithms._3_CyclesInGraph;

import java.util.*;

public class CyclesInGraph {
    private static Map<Character, List<Character>> graph = new LinkedHashMap<>();
    private static Set<Character> cycleNode = new TreeSet<>();
    private static Set<Character> visited = new TreeSet<>();
    private static boolean cycle = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String line = in.nextLine();
            Character node1 = line.charAt(0);
            Character node2 = line.charAt(2);

            if (!graph.containsKey(node1)) {
                graph.put(node1, new ArrayList<>());
            }
            if (!graph.containsKey(node2)) {
                graph.put(node2, new ArrayList<>());
            }
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        for (Character node : graph.keySet()) {
            if (!visited.contains(node)) {
                cyclesInGraph(node, '0');
            }
        }

        System.out.println("Acyclic: " + (cycle ? "No" : "Yes"));
    }

    private static void cyclesInGraph(Character node, Character prev) {
        visited.add(node);
        cycleNode.add(node);
        for (Character child : graph.get(node)) {
            if (child != prev) {
                if (!visited.contains(child)) {
                    cyclesInGraph(child, node);
                }
                if (cycleNode.contains(child)) {
                    cycle = true;
                }
            }
        }
        cycleNode.remove(node);
    }
}
