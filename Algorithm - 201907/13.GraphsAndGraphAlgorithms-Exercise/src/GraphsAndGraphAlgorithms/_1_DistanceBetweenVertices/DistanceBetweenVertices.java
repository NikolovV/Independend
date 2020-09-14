package GraphsAndGraphAlgorithms._1_DistanceBetweenVertices;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class DistanceBetweenVertices {
    private static Map<Integer, List<Integer>> graph = new LinkedHashMap<>();
    private static List<Pair<Integer, Integer>> pair = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfVertices = Integer.parseInt(in.nextLine());

        int numberOfPairs = Integer.parseInt(in.nextLine());

        for (int i = 0; i < numberOfVertices; i++) {
            String[] line = in.nextLine().split(":");
            List<Integer> child = line.length > 1 ? Arrays.stream(line[1].split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList()) :
                    new ArrayList<>();
            graph.put(Integer.parseInt(line[0]), child);
        }

        for (int i = 0; i < numberOfPairs; i++) {
            String[] line = in.nextLine().split("-");
            pair.add(new Pair<>(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        }

        distanceBetweenVertices();
    }

    private static void distanceBetweenVertices() {
        for (Pair<Integer, Integer> currentPair : pair) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(currentPair.getKey());
            Map<Integer, Integer> distance = new HashMap<>();
            distance.put(currentPair.getKey(), 0);

            while (queue.size() != 0) {
                Integer currentNode = queue.poll();
                for (Integer child : graph.get(currentNode)) {
                    if (!distance.containsKey(child)) {
                        distance.put(child, distance.get(currentNode) + 1);
                        queue.add(child);
                    }
                }
            }
            System.out.println(String.format("{%d, %d} -> %d", currentPair.getKey(), currentPair.getValue(),
                    distance.get(currentPair.getValue()) == null ? -1 : distance.get(currentPair.getValue())));
        }
    }
}
