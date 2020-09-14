package _2_BiConnectedComponents;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.IntStream;

public class BiConnectedComponents {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] parent;
    private static int[] depths;
    private static int[] lowpoint;
    private static final List<List<Integer>> pathToArticulation = new ArrayList<>();
    private static final Stack<Pair<Integer, Integer>> components = new Stack<>();

    public static void main(String[] args) {
        readInput();
        findArticulationPoints(graph);
        System.out.println("Number of bi-connected components: " + pathToArticulation.size());
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);
        int nodes = Integer.parseInt(in.nextLine().split(" ")[1]);
        int edges = Integer.parseInt(in.nextLine().split(" ")[1]);

        graph = new ArrayList[nodes];
        IntStream.range(0, nodes).forEach(i -> graph[i] = new ArrayList<>());

        for (int i = 0; i < edges; i++) {
            String[] elements = in.nextLine().split(" ");
            int node = Integer.parseInt(elements[0]);
            int child = Integer.parseInt(elements[1]);

            graph[node].add(child);
            graph[child].add(node);
        }
    }

    private static void findArticulationPoints(List<Integer>[] targetGraph) {
        graph = targetGraph;
        visited = new boolean[targetGraph.length];
        parent = new int[targetGraph.length];
        Arrays.fill(parent, -1);
        depths = new int[targetGraph.length];
        lowpoint = new int[targetGraph.length];

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                findArticulationPoint(node, 1);
            }
        }
    }

    private static void findArticulationPoint(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowpoint[node] = depth;
        for (int child : graph[node]) {
            if (!visited[child]) {
                parent[child] = node;
                findArticulationPoint(child, depth + 1);

                components.add(new Pair<>(node, child));

                if (lowpoint[child] >= depths[node]) {
                    List<Integer> articulationComponent = new ArrayList<>();
                    Pair<Integer, Integer> edge = components.peek();

                    articulationComponent.add(edge.getKey());
                    if (!components.isEmpty()) {
                        do {
                            edge = components.pop();
                            articulationComponent.add(edge.getValue());
                        } while (!components.isEmpty() && (edge.getKey() != node || components.peek().getKey().equals(edge.getValue())));

                        pathToArticulation.add(articulationComponent);
                    }

                }
                lowpoint[node] = Math.min(lowpoint[node], lowpoint[child]);
            } else if (child != parent[node]) {
                lowpoint[node] = Math.min(lowpoint[node], depths[child]);
            }
        }
    }
}
