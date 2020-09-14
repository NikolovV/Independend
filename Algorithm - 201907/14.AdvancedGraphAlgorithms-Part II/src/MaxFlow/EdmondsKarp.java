package MaxFlow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class EdmondsKarp {
    private static int[][] graph;
    private static int[] parents;

    public static int findMaxFlow(int[][] targetGraph) {

        graph = targetGraph;
        parents = new int[targetGraph.length];
        Arrays.fill(parents, -1);
        int maxFlow = 0;
        int start = 0;
        int end = graph.length - 1;

        while (bfs(start, end)) {
            int pathFlow = Integer.MAX_VALUE;
            int currentNode = end;
            while (currentNode != start) {
                int previousNode = parents[currentNode];
                int currentFlow = graph[previousNode][currentNode];
                if (currentFlow > 0 && currentFlow < pathFlow) {
                    pathFlow = currentFlow;
                }
                currentNode = previousNode;
            }
            maxFlow += pathFlow;

            currentNode = end;
            while (currentNode != start) {
                int previousNode = parents[currentNode];
                graph[previousNode][currentNode] -= pathFlow;
                graph[currentNode][previousNode] += pathFlow;

                currentNode = previousNode;
            }
        }
        return maxFlow;
    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int child = 0; child < graph[currentNode].length; child++) {
                if (graph[currentNode][child] > 0 && !visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                    parents[child] = currentNode;
                }
            }
        }

        return visited[end];
    }
}
