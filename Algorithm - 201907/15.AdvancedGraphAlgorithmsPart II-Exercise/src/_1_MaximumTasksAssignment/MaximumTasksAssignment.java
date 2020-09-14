package _1_MaximumTasksAssignment;

import java.util.*;
import java.util.stream.IntStream;

public class MaximumTasksAssignment {
    private static int[][] graph;
    private static int[] parents;
    private static int personCount;

    public static void main(String[] args) {
        buildGraph();

        maxTaskAssignment();

        Set<String> result = rebuildPath();
        result.forEach(System.out::println);
    }

    private static void buildGraph() {
        Scanner in = new Scanner(System.in);
        personCount = Integer.parseInt(in.nextLine().split(" ")[1]);
        int tasks = Integer.parseInt(in.nextLine().split(" ")[1]);

        int nodes = tasks + personCount + 2;
        graph = new int[nodes][nodes];
        IntStream.range(0, personCount).forEach(i -> graph[0][i + 1] = 1);
        IntStream.range(0, tasks).forEach(i -> graph[i + personCount + 1][graph.length - 1] = 1);

        for (int person = 0; person < personCount; person++) {
            String line = in.nextLine();
            for (int task = 0; task < tasks; task++) {
                if (line.charAt(task) == 'Y') {
                    graph[person + 1][task + personCount + 1] = 1;
                }
            }
        }

        parents = new int[graph.length];
        Arrays.fill(parents, -1);
    }

    private static void maxTaskAssignment() {
        int start = 0;
        int end = graph.length - 1;

        while (bfs(start, end)) {

            int currentNode = end;
            while (currentNode != start) {
                int parent = parents[currentNode];
                graph[parent][currentNode] = 0;
                graph[currentNode][parent] = 1;

                currentNode = parent;
            }
        }
    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> que = new ArrayDeque<>();

        que.offer(start);
        visited[start] = true;

        while (!que.isEmpty()) {
            int currentNode = que.poll();
            for (int child = 0; child < graph[currentNode].length; child++) {
                if (graph[currentNode][child] > 0 && !visited[child]) {
                    que.offer(child);
                    visited[child] = true;
                    parents[child] = currentNode;
                }
            }
        }
        return visited[end];
    }

    private static Set<String> rebuildPath() {
        int start = 0;
        int end = graph.length - 1;
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];
        Set<String> result = new TreeSet<>();

        que.offer(end);
        visited[end] = true;

        while (!que.isEmpty()) {
            int currentNode = que.poll();

            for (int child = 0; child < graph.length; child++) {
                if (!visited[child] && graph[currentNode][child] > 0) {
                    que.offer(child);
                    visited[child] = true;
                    if (currentNode != end && currentNode != start && child != end && child != start) {
                        result.add((char) ((child - 1) + 'A') + "-" + (currentNode - personCount));
                    }
                }
            }
        }

        return result;
    }
}