package ArticulationPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] parent;
    private static int[] depths;
    private static int[] lowpoint;
    private static List<Integer> articulationPoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        graph = targetGraph;
        visited = new boolean[targetGraph.length];
        parent = new int[targetGraph.length];
        Arrays.fill(parent, -1);
        depths = new int[targetGraph.length];
        lowpoint = new int[targetGraph.length];
        articulationPoints = new ArrayList<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                findArticulationPoint(node, 1);
            }
        }
        return articulationPoints;
    }

    private static void findArticulationPoint(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowpoint[node] = depth;
        int childCount = 0;
        boolean isArticulation = false;
        for (int child : graph[node]) {
            if (!visited[child]) {
                parent[child] = node;
                findArticulationPoint(child, depth + 1);
                childCount++;
                if (lowpoint[child] >= depths[node]) {
                    isArticulation = true;
                }
                lowpoint[node] = Math.min(lowpoint[node], lowpoint[child]);
            } else if (child != parent[node]) {
                lowpoint[node] = Math.min(lowpoint[node], depths[child]);
            }
        }
        if ((parent[node] != -1 && isArticulation) || (parent[node] == -1 && childCount > 1)) {
            articulationPoints.add(node);
        }
    }
}
