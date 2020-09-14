package ConnectedComponents;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static boolean[] used;

    public static void main(String[] args) {
        List<List<Integer>> graph = readGraph();

        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);
        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.println("Connected component: "
                    + connectedComponent.stream().map(String::valueOf).collect(Collectors.joining(" ")).toString());
        }
    }

    private static List<List<Integer>> readGraph() {
        Scanner in = new Scanner(System.in);

        List<List<Integer>> graph = new ArrayList<>();
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            List<Integer> connectedComponents = new ArrayList<>();

            String line = in.nextLine();
            if (line.equals("")) {
                graph.add(connectedComponents);
                continue;
            }
            String[] nodes = line.split(" ");

            for (String node : nodes) {
                connectedComponents.add(Integer.parseInt(node));
            }

            graph.add(connectedComponents);
        }
        return graph;
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> result = new ArrayList<>();
        used = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            Deque<Integer> deque = new ArrayDeque<>();
            if (!used[i]) {
                dfs(graph, i, deque);
                result.add(deque);
            }
        }

        return result;
    }

    public static void dfs(List<List<Integer>> node, int index, Deque<Integer> deque) {
        if (!used[index]) {
            used[index] = true;
            for (Integer child : node.get(index)) {
                dfs(node, child, deque);
            }
            deque.add(index);
        }
    }
}

