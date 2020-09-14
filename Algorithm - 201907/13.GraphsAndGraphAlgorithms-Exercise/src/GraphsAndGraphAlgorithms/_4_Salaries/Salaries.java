package GraphsAndGraphAlgorithms._4_Salaries;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Salaries {
    private static List<List<Integer>> graph = new ArrayList<>();
    private static Map<Integer, Integer> predecessorCount;
    private static Set<Integer> visited = new TreeSet<>();
    private static int[] cashed;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int employee = Integer.parseInt(in.nextLine());
        for (int i = 0; i < employee; i++) {
            String line = in.nextLine();
            List<Integer> childEmployee = IntStream.range(0, line.length())
                    .filter(j -> line.charAt(j) == 'Y')
                    .boxed()
                    .collect(Collectors.toList());
            graph.add(childEmployee);
        }

        getPredecessorCount();
        cashed = new int[graph.size()];

        List<Integer> startNodes = predecessorCount.keySet()
                .stream()
                .filter(x -> predecessorCount.get(x) == 0)
                .collect(Collectors.toList());

        for (Integer node : startNodes) {
            dfs(node);
        }

        System.out.println(graph.size() == 1 ? 1 : Arrays.stream(cashed).sum());
    }

    private static int dfs(Integer node) {
        visited.add(node);
        for (Integer child : graph.get(node)) {
            if (!visited.contains(child)) {
                cashed[child] += dfs(child);
            }
            cashed[node] += cashed[child];
        }
        if (cashed[node] != 0) {
            return cashed[node];
        }
        return 1;
    }

    private static void getPredecessorCount() {
        predecessorCount = new HashMap<>();
        for (int node = 0; node < graph.size(); node++) {
            if (!predecessorCount.containsKey(node)) {
                predecessorCount.put(node, 0);
            }
            for (Integer child : graph.get(node)) {
                if (!predecessorCount.containsKey(child)) {
                    predecessorCount.put(child, 0);
                }
                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }
    }
}
