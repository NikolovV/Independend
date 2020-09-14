package StronglyConnectedComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StronglyConnectedComponents {
    private static boolean[] visited;
    private static Stack<Integer> dfsNodeStack;
    private static List<Integer>[] graph;
    private static List<Integer>[] reversedGraph;
    private static int size;

    private static List<List<Integer>> stronglyConnectedComponents;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        stronglyConnectedComponents = new ArrayList<>();
        dfsNodeStack = new Stack<>();
        graph = targetGraph;
        size = targetGraph.length;

        buildReversedGraph();

        visited = new boolean[size];
        for (int node = 0; node < size; node++) {
            if (!visited[node]) {
                dfs(node);
            }
        }

        visited = new boolean[size];
        while (dfsNodeStack.size() > 0) {
            int currentNode = dfsNodeStack.pop();
            if (!visited[currentNode]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reversedDfd(currentNode);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void buildReversedGraph() {
        reversedGraph = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            reversedGraph[i] = new ArrayList<>();
        }
        for (int node = 0; node < size; node++) {
            for (int child : graph[node]) {
                reversedGraph[child].add(node);
            }
        }
    }

    private static void dfs(int node) {
        if (!visited[node]) {
            visited[node] = true;
            for (int child : graph[node]) {
                dfs(child);
            }

            dfsNodeStack.push(node);
        }
    }

    private static void reversedDfd(int node) {
        if (!visited[node]) {
            visited[node] = true;
            stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);
            for (int child : reversedGraph[node]) {
                reversedDfd(child);
            }
        }
    }

}
