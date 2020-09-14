package _3_SupplementGraphToStronglyConnected;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SupplementGraphToStronglyConnected {
    private static boolean[] visited;
    private static Stack<Integer> dfsPath;
    private static List<Integer>[] graph;
    private static List<Integer>[] reversedGraph;
    private static List<List<Integer>> stronglyConnectedComponents;
    private static List<List<Integer>> sccGraph;

    public static void main(String[] args) {
        readInput();
        stronglyConnectedComponents = findStronglyConnectedComponents();
        connectComponents();
        connectStronglyGraph();
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);
        int nodes = Integer.parseInt(in.nextLine().split(" ")[1]);
        int edges = Integer.parseInt(in.nextLine().split(" ")[1]);

        graph = new ArrayList[nodes];
        IntStream.range(0, nodes).forEach(i -> graph[i] = new ArrayList<>());

        for (int i = 0; i < edges; i++) {
            String[] line = in.nextLine().split(" ");
            int nodeA = Integer.parseInt(line[0]);
            int nodeB = Integer.parseInt(line[2]);

            graph[nodeA].add(nodeB);
        }
    }

    public static List<List<Integer>> findStronglyConnectedComponents() {
        dfsPath = new Stack<>();
        int size = graph.length;

        buildReversedGraph();

        visited = new boolean[size];
        for (int node = 0; node < size; node++) {
            if (!visited[node]) {
                dfs(node);
            }
        }

        stronglyConnectedComponents = new ArrayList<>();
        visited = new boolean[size];
        while (dfsPath.size() > 0) {
            int currentNode = dfsPath.pop();
            if (!visited[currentNode]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reversedDfd(currentNode);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void buildReversedGraph() {
        reversedGraph = new ArrayList[graph.length];
        for (int i = 0; i < graph.length; i++) {
            reversedGraph[i] = new ArrayList<>();
        }
        for (int node = 0; node < graph.length; node++) {
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

            dfsPath.push(node);
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

    private static void connectComponents() {
        sccGraph = new ArrayList<>();
        IntStream.range(0, stronglyConnectedComponents.size()).forEach(i -> sccGraph.add(new ArrayList<>()));

        for (int i = 0; i < stronglyConnectedComponents.size(); i++) {
            for (int node : stronglyConnectedComponents.get(i)) {
                List<Integer> originChild = graph[node];

                List<Integer> destNodeList = stronglyConnectedComponents.stream()
                        .filter(n -> !Collections.disjoint(n, originChild))
                        .map(l -> stronglyConnectedComponents.indexOf(l)).collect(Collectors.toList());
                int currentNode = i;
                sccGraph.get(i).addAll(destNodeList.stream()
                        .filter(n -> !sccGraph.get(currentNode).contains(n))
                        .filter(n -> currentNode != n)
                        .collect(Collectors.toList()));
            }
        }
    }

    private static void connectStronglyGraph() {
        List<Integer> edgesWithoutOut = IntStream.range(0, sccGraph.size())
                .filter(n -> sccGraph.get(n).size() == 0)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> edgesWithoutIn = new ArrayList(IntStream.range(0, sccGraph.size())
                .filter(n -> !sccGraph.stream()
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet())
                        .contains(n))
                .boxed().collect(Collectors.toList()));

        List<String> newEdges = new ArrayList<>();
        if (edgesWithoutOut.size() > edgesWithoutIn.size()) {
            for (int i = 0; i < edgesWithoutOut.size(); i++) {
                int sourceNode = edgesWithoutIn.get(i);
                int destNode;
                if (i < edgesWithoutIn.size()) {
                    destNode = edgesWithoutIn.get(i);
                } else {
                    List<Integer> edgesInOut = IntStream.range(0, sccGraph.size())
                            .filter(n -> !edgesWithoutOut.contains(n))
                            .filter(n -> !edgesWithoutIn.contains(n))
                            .boxed()
                            .collect(Collectors.toList());
                    destNode = edgesInOut.iterator().next();
                }
                newEdges.add(String.format("%d -> %d",
                        stronglyConnectedComponents.get(sourceNode).iterator().next(),
                        stronglyConnectedComponents.get(destNode).iterator().next()));
                sccGraph.get(sourceNode).add(destNode);
            }
        } else {
            for (int i = 0; i < edgesWithoutIn.size(); i++) {
                int destNode = edgesWithoutIn.get(i);
                int sourceNode = 0;
                if (i < edgesWithoutOut.size()) {
                    sourceNode = edgesWithoutOut.get(i);
                } else {
                    List<Integer> edgesInOut = IntStream.range(0, sccGraph.size())
                            .filter(n -> !edgesWithoutOut.contains(n))
                            .filter(n -> !edgesWithoutIn.contains(n))
                            .boxed()
                            .collect(Collectors.toList());

                    sourceNode = edgesInOut.iterator().next();
                }
                newEdges.add(String.format("%d -> %d",
                        stronglyConnectedComponents.get(sourceNode).iterator().next(),
                        stronglyConnectedComponents.get(destNode).iterator().next()));
                sccGraph.get(sourceNode).add(destNode);
            }
        }
        int newEdgesCount = Math.max(edgesWithoutIn.size(), edgesWithoutOut.size());
        System.out.println("New edges needed: " + newEdgesCount);
        newEdges.forEach(System.out::println);
    }

}
