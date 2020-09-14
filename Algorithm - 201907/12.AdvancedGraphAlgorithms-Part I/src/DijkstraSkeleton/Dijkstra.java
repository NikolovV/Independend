package DijkstraSkeleton;

import java.util.*;

public class Dijkstra {
    private static int[] distance;
    private static boolean[] used;
    private static int[] previous;

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        used = new boolean[graph.length];
        distance = new int[graph.length];
        previous = new int[graph.length];
        previous[0] = -1;
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[sourceNode] = 0;

        Set<Node> priorityQueue = new TreeSet<>();
        priorityQueue.add(new Node(sourceNode, 0));
        used[0] = true;

        while (!priorityQueue.isEmpty()) {
            Node minNode = priorityQueue.iterator().next();
            priorityQueue.remove(minNode);

            if (distance[minNode.id] == Integer.MAX_VALUE) {
                break;
            }

            for (int node = 0; node < graph.length; node++) {
                if (!used[node] && graph[minNode.id][node] > 0) {
                    priorityQueue.add(new Node(node, distance[minNode.id] + graph[minNode.id][node]));
                    used[node] = true;
                }
                int newDistance = distance[minNode.id] + graph[minNode.id][node];
                if (newDistance < distance[node] && graph[minNode.id][node] > 0) {
                    distance[node] = newDistance;
                    previous[node] = minNode.id;
                    priorityQueue.add(new Node(node, distance[minNode.id] + graph[minNode.id][node]));
                }
            }
        }
        return reconstructPath(sourceNode, destinationNode);
    }

//    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
//        used = new boolean[graph.length];
//        previous = new int[graph.length];
//        previous[0] = -1;
//
//        distance = new int[graph.length];
//        Arrays.fill(distance, Integer.MAX_VALUE);
//        distance[sourceNode] = 0;
//
//        while (true) {
//            int minDistance = Integer.MAX_VALUE;
//            int minNode = 0;
//            for (int node = 0; node < graph.length; node++) {
//                if (!used[node] && distance[node] < minDistance) {
//                    minDistance = distance[minNode];
//                    minNode = node;
//                }
//            }
//            if (minDistance == Integer.MAX_VALUE) {
//                break;
//            }
//            used[minNode] = true;
//
//            for (int i = 0; i < graph.length; i++) {
//                if (graph[minNode][i] > 0) {
//                    int newDistance = distance[minNode] + graph[minNode][i];
//                    if (newDistance < distance[i]) {
//                        distance[i] = newDistance;
//                        previous[i] = minNode;
//                    }
//                }
//            }
//        }
//
//        return reconstructPath(sourceNode, destinationNode);
//    }

    private static List<Integer> reconstructPath(int sourceNode, int destinationNode) {
        if (distance[destinationNode] == Integer.MAX_VALUE) {
            return null;
        }
        List<Integer> path = new ArrayList<>();
        int currentNode = destinationNode;
        while (currentNode != -1) {
            path.add(currentNode);
            currentNode = previous[currentNode];
        }

        Collections.reverse(path);

        return path;
    }

}
