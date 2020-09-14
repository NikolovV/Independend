package DijkstraSkeleton;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static int[][] graph;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int src = Integer.parseInt(in.nextLine());
        int dest = Integer.parseInt(in.nextLine());
        printPath(graph, src, dest);
    }

    private static void printPath(int[][] graph, int sourceNode, int destinationNode) {
        System.out.println(String.format("Shortest path [{0} -> {1}]: ", sourceNode, destinationNode));

        List<Integer> path = Dijkstra.dijkstraAlgorithm(graph, sourceNode, destinationNode);

        if (path == null) {
            System.out.println("no path");
        } else {
            int pathLength = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                pathLength += graph[path.get(i)][path.get(i + 1)];
            }

            System.out.println(String.format("{0} (length {1})", path, pathLength));
        }
    }
}
