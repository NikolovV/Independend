package Exam_20190928._4_CheapTownTour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class CheapTownTour {
    public static int nodes;
    public static int edgesCount;
    public static Edge[] edges;
    public static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nodes = Integer.parseInt(reader.readLine());
        edgesCount = Integer.parseInt(reader.readLine());
        roots = new int[nodes + 1];

        edges = new Edge[edgesCount];

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" - "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            edges[i] = new Edge(tokens[0], tokens[1], tokens[2]);
        }

        for (int i = 0; i < nodes + 1; i++) {
            roots[i] = -1;
        }

        System.out.printf("Total cost: %d", kruskal());
    }

    public static int kruskal() {
        Arrays.sort(edges, Comparator.comparingInt(f -> f.weight));

        int minimumSpanningTree = 0;
        for (int i = 0; i < edgesCount; i++) {
            int firstRoot = getRoot(edges[i].from);
            int secondRoot = getRoot(edges[i].to);
            if (firstRoot != secondRoot) {
                minimumSpanningTree += edges[i].weight;
                roots[secondRoot] = firstRoot;
            }
        }

        return minimumSpanningTree;
    }

    public static int getRoot(int nodeNumber) {
        int root = nodeNumber;
        int prevRoot;
        while (-1 != roots[root]) {
            root = roots[root];
        }

        while (nodeNumber != root) {
            prevRoot = nodeNumber;
            nodeNumber = roots[nodeNumber];
            roots[prevRoot] = root;
        }

        return root;
    }

    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
