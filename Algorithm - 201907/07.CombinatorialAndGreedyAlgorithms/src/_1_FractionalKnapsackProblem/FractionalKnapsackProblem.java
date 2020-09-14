package _1_FractionalKnapsackProblem;

import javafx.util.Pair;

import java.util.*;

public class FractionalKnapsackProblem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int capacity = Integer.parseInt(in.nextLine().substring(10));

        int itemCount = Integer.parseInt(in.nextLine().substring(7));
        Map<Integer, List<Integer>> elements = new HashMap<>();
        for (int i = 0; i < itemCount; i++) {
            String[] currentElement = in.nextLine().split(" -> ");
            Integer key = Integer.parseInt(currentElement[0]);
            if (elements.containsKey(key)) {
                elements.get(key).add(Integer.parseInt(currentElement[1]));
            } else {
                elements.put(Integer.parseInt(currentElement[0]), new ArrayList() {{
                    this.add(Integer.parseInt(currentElement[1]));
                }});
            }
        }

        fractionalKnapsack(elements, capacity);
    }

    private static void fractionalKnapsack(Map<Integer, List<Integer>> elements, int capacity) {
        double totalSum = 0;
        while (capacity > 0 && !elements.isEmpty()) {
            double percentage = 0;
            Pair<Integer, Integer> maxKeyValuePair = maxKeyValueElement(elements);
            int weight = maxKeyValuePair.getValue();
            if (capacity > weight) {
                capacity -= weight;
                percentage = 100;
            } else {
                percentage = ((double) capacity / weight) * 100;
                capacity = 0;
            }
            totalSum += maxKeyValuePair.getKey() * percentage / 100;

            if (percentage == 100D) {
                System.out.printf("Take %d%% of item with price %.2f and weight %.2f\n", (int) percentage,
                        (double) maxKeyValuePair.getKey(),
                        (double) maxKeyValuePair.getValue());
            } else {
                System.out.printf("Take %.2f%% of item with price %.2f and weight %.2f\n", percentage,
                        (double) maxKeyValuePair.getKey(),
                        (double) maxKeyValuePair.getValue());
            }

            if (elements.get(maxKeyValuePair.getKey()).size() > 1) {
                elements.get(maxKeyValuePair.getKey()).remove((Object) maxKeyValuePair.getValue());
            } else {
                elements.remove((Object) maxKeyValuePair.getKey());
            }
        }
        System.out.printf("Total price: %.2f", totalSum);
    }

    private static Pair<Integer, Integer> maxKeyValueElement(Map<Integer, List<Integer>> elements) {
        double bestCombo = 0;
        int bestKey = 0;
        int bestValue = 0;
        for (Integer key : elements.keySet()) {
            for (Integer values : elements.get(key)) {
                if ((double) key / values > bestCombo) {
                    bestCombo = (double) key / values;
                    bestKey = key;
                    bestValue = values;
                }
            }
        }
        Pair<Integer, Integer> maxKeyValuePair = new Pair<>(bestKey, bestValue);

        return maxKeyValuePair;
    }

}
