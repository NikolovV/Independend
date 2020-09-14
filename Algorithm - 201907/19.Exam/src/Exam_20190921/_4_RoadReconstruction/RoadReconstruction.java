package Exam_20190921._4_RoadReconstruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoadReconstruction {
    private static int[][] graph;
    private static final StringBuilder output = new StringBuilder();
    private static int time = 0;
    private static boolean[] visited;
    private static int[] disc;
    private static int[] low;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        graph = new int[nodes][nodes];
        visited = new boolean[graph.length];
        disc = new int[graph.length];
        low = new int[graph.length];
        parent = new int[graph.length];

        for (int i = 0; i < edges; i++) {
            String[] tokens = reader.readLine().split(" - ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            graph[from][to] = 1;
            graph[to][from] = 1;
        }

        bridges();

        System.out.println("Important streets:");
        System.out.print(output.toString());
    }

    private static void bridges() {

        for (int i = 0; i < graph.length; i++) {
            parent[i] = -1;
        }

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                findBridge(i);
            }
        }
    }

    private static void findBridge(int node) {
        visited[node] = true;
        disc[node] = low[node] = ++time;

        for (int child = 0; child < graph.length; child++) {
            if (graph[node][child] != 0) {
                if (!visited[child]) {
                    parent[child] = node;
                    findBridge(child);

                    low[node] = Math.min(low[node], low[child]);
                    if (low[child] > disc[node]) {
                        output.append(String.format("%d %d\n", node, child));
                    }
                } else if (child != parent[node]) {
                    low[node] = Math.min(low[node], disc[child]);
                }
            }
        }
    }

}
