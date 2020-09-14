package _5_SumWithLimitedAmountOfCoins;

import java.util.Arrays;
import java.util.Scanner;

public class SumWithLimitedAmountOfCoins {
    private static int[] coins;
    private static int[][] F;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        coins = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int totalSum = in.nextInt();
        F = new int[totalSum + 1][coins.length];

        System.out.println(count(totalSum, coins.length - 1));
    }

    private static long count(int sum, int k) {
        if (sum <= 0 || k < 0) {
            return 0;
        }
        if (F[sum][k] > 0) {
            return F[sum][k];
        } else {
            if (coins[k] == sum) {
                F[sum][k] = 1;
            }
            F[sum][k] += count(sum - coins[k], k - 1);
            int j = k;
            while (j >= 0 && coins[j] == coins[k]) {
                j--;
            }
            F[sum][k] += count(sum, j);
        }
        return F[sum][k];
    }
}
