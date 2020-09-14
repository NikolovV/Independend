package KruskalSkeleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {
    private static int[] parent;

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        List<Edge> result = new ArrayList<>();
        List<Edge> edgeSorted = new ArrayList<>();
        parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }

        edgeSorted.addAll(edges);
        Collections.sort(edgeSorted);

        for (Edge edge : edgeSorted) {
            int rootA = findRoot(edge.getStartNode(), parent);
            int rootB = findRoot(edge.getEndNode(), parent);
            if (rootA != rootB) {
                result.add(edge);
                parent[rootB] = rootA;
            }

        }
        return result;
    }

    public static int findRoot(int node, int[] parent) {
        int root = node;
        while (parent[root] != root) {
            root = parent[root];
        }

        while (node != root) {
            int oldParent = parent[node];
            parent[node] = root;
            node = oldParent;

        }
        return root;
    }
}
