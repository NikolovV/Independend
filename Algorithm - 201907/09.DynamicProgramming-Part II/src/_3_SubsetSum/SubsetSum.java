package _3_SubsetSum;

import java.util.HashMap;
import java.util.Map;

public class SubsetSum {
    public static void main(String[] args) {
        int[] numbers = new int[]{3, 5, 1, 4, 2};

        Map<Integer, Integer> sums = calcSumsWithoutRepeat(numbers);

        int targetSum = 9;
        if (sums.containsKey(targetSum)) {
            System.out.println("Yes");
            while (targetSum != 0) {
                int number = sums.get(targetSum);
                System.out.print(number + " ");
                targetSum -= number;
            }
        } else {
            System.out.println("No");
        }
    }

    private static Map<Integer, Integer> calcSumsWithoutRepeat(int[] numbers) {
        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 0);

        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];

            Map<Integer, Integer> tmp = new HashMap<>(result);
            for (int number : tmp.keySet()) {
                int newSum = number + currentNumber;
                if (!result.containsKey(newSum)) {
                    result.put(newSum, currentNumber);
                }
            }
        }

        return result;
    }

}
