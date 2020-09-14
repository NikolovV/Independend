package PartIII_Exercise._4_ChainLightning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChainLightning {
    private static List<Integer>[] graph;
    private static int[] nodeDamage;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int neighbourhoodsCount = Integer.parseInt(in.readLine());
        int edgesCount = Integer.parseInt(in.readLine());
        int lightningsCount = Integer.parseInt(in.readLine());
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            String[] graphData = in.readLine().split(" ");
            int from = Integer.parseInt(graphData[0]);
            int to = Integer.parseInt(graphData[1]);
            int distance = Integer.parseInt(graphData[2]);
            Edge edge = new Edge(from, to, distance);

            edges.add(edge);
        }

        List<Edge> spanningForestEdges = kruskal(neighbourhoodsCount, edges);
        graph = new ArrayList[neighbourhoodsCount];

        for (int i = 0; i < neighbourhoodsCount; i++) {
            graph[i] = new ArrayList<>();
        }
        for (Edge edge : spanningForestEdges) {
            graph[edge.getStartNode()].add(edge.getEndNode());
            graph[edge.getEndNode()].add(edge.getStartNode());
        }

        nodeDamage = new int[neighbourhoodsCount];
        for (int i = 0; i < lightningsCount; i++) {
            String[] lightningStrikesData = in.readLine().split(" ");
            int node = Integer.parseInt(lightningStrikesData[0]);
            int damage = Integer.parseInt(lightningStrikesData[1]);
            boolean[] visited = new boolean[neighbourhoodsCount];
            DFS(node, damage, visited);
        }

        int maxDamage = 0;
        for (int currentDamage : nodeDamage) {
            if (currentDamage > maxDamage) {
                maxDamage = currentDamage;
            }
        }
        System.out.print(maxDamage);
    }

    private static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        List<Edge> spanningTree = new ArrayList<>();
        int[] parent = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }
        edges.sort(Comparator.naturalOrder());

        for (Edge edge : edges) {
            int startNodeRoot = findRoot(edge.getStartNode(), parent);
            int endNodeRoot = findRoot(edge.getEndNode(), parent);

            if (startNodeRoot != endNodeRoot) {
                spanningTree.add(edge);
                parent[startNodeRoot] = endNodeRoot;
            }
        }

        return spanningTree;
    }

    private static int findRoot(int node, int[] parent) {
        int root = node;

        while (parent[root] != root) {
            root = parent[root];
        }

        while (node != root) {
            int previousParent = parent[node];
            parent[node] = root;
            node = previousParent;
        }

        return root;
    }

    private static void DFS(int node, int damage, boolean[] visited) {
        visited[node] = true;
        nodeDamage[node] += damage;

        for (int child : graph[node]) {
            if (!visited[child]) {
                DFS(child, damage / 2, visited);
            }
        }
    }

}

class Edge implements Comparable<Edge> {
    private final int startNode;
    private final int endNode;
    private final int distance;

    Edge(int startNode, int endNode, int distance) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.distance = distance;
    }

    public int getStartNode() {
        return this.startNode;
    }

    public int getEndNode() {
        return this.endNode;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.distance, other.distance);
    }
}
