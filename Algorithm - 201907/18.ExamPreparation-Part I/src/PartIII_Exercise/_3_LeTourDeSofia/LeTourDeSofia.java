package PartIII_Exercise._3_LeTourDeSofia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class LeTourDeSofia {
    private static Set<Integer>[] graph;
    private static boolean[] visited;
    private static int[] distanceTo;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int junctionCount = Integer.parseInt(in.readLine());
        int streetCount = Integer.parseInt(in.readLine());
        int startingNode = Integer.parseInt(in.readLine());

        graph = new HashSet[junctionCount];
        for (int i = 0; i < junctionCount; i++) {
            graph[i] = new HashSet<>();
        }
        visited = new boolean[graph.length];
        distanceTo = new int[graph.length];

        for (int i = 0; i < streetCount; i++) {
            String[] graphData = in.readLine().split(" ");
            int from = Integer.parseInt(graphData[0]);
            int to = Integer.parseInt(graphData[1]);
            graph[from].add(to);
        }

        BFS(startingNode);

        boolean found = false;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < graph.length; i++) {
            if (visited[i] && graph[i].contains(startingNode) && distanceTo[i] + 1 < min) {
                found = true;
                min = distanceTo[i] + 1;
            }
        }

        if (found) {
            System.out.println(min);
        } else {
            int count = 0;
            for (boolean b : visited) {
                if (b) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static void BFS(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        distanceTo[start] = 0;
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child : graph[node]) {
                if (!visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                    distanceTo[child] = distanceTo[node] + 1;
                }
            }
        }
    }
}
