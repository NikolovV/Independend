package _3_DividingPresents;

import java.util.Arrays;
import java.util.Scanner;

public class DividingPresents {
    private static int[] presents;
    private static int[] sumPresents;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        presents = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int totalPresentsSum = Arrays.stream(presents).sum();
        sumPresents = new int[totalPresentsSum + 1];
        Arrays.fill(sumPresents, -1);
        sumPresents[0] = 0;

        devidePresents(totalPresentsSum);

    }

    private static void devidePresents(int totalSum) {
        for (int presentIndex = 0; presentIndex < presents.length; presentIndex++) {
            int currentPresent = presents[presentIndex];
            for (int prevSumIndex = totalSum - presents[presentIndex]; prevSumIndex >= 0; prevSumIndex--) {
                if (sumPresents[prevSumIndex] != -1 && sumPresents[prevSumIndex + currentPresent] == -1) {
                    sumPresents[prevSumIndex + currentPresent] = presentIndex;
                }
            }
        }

        int half = totalSum / 2;

        for (int i = half; i >= 0; --i) {
            if (sumPresents[i] != -1) {
                System.out.println("Difference: " + (totalSum - i - i));
                System.out.println("Alan:" + i + " Bob:" + (totalSum - i));
                System.out.print("Alan takes:");

                while (i != 0) {
                    System.out.print(" " + presents[sumPresents[i]]);
                    i -= presents[sumPresents[i]];
                }
                System.out.println("\nBob takes the rest.");
            }
        }
    }

}
