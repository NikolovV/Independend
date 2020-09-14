package _4_SumWithUnlimitedAmountOfCoins;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SumWithUnlimitedAmountOfCoins {
    private static int[] coins;
    private static int[] possibleSum;
    private static Map<String, Integer> possibleCombo = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        coins = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int totalSum = in.nextInt();
        possibleSum = new int[totalSum + 1];
        possibleSum[0] = 1;

        System.out.println(sumCoins(totalSum));
    }

    private static int TopDownSumCoins(int index, int targetSum) {
        if (index < 0 || targetSum < 0) {
            return 0;
        }
        if (targetSum == 0) {
            return 1;
        }
        String key = index + "|" + targetSum;
        if (!possibleCombo.containsKey(key)) {
            int include = TopDownSumCoins(index, targetSum - coins[index]);
            int exclude = TopDownSumCoins(index - 1, targetSum);
            possibleCombo.put(key, include + exclude);
        }
        return possibleCombo.get(key);
    }

    private static int sumCoins(int targetSum) {

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= targetSum; j++) {
                possibleSum[j] += possibleSum[j - coins[i]];
            }
        }

        return possibleSum[targetSum];
    }
}
