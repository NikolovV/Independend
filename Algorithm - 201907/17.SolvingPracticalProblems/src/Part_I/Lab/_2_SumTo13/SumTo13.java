package Part_I.Lab._2_SumTo13;

import java.util.Arrays;
import java.util.Scanner;

public class SumTo13 {
    private static int[] numbers;
    private static final int totalSum = 13;
    private static final int numbersCount = 3;
    private static boolean canBeSummed = false;

    public static void main(String[] args) {
        readInput();
        sumTo13(0, 0);
        System.out.println(canBeSummed ? "Yes" : "No");
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);
        numbers = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void sumTo13(int index, int sum) {
        if (index == numbersCount) {
            if (sum == totalSum) {
                canBeSummed = true;
            }
            return;
        }
        if (canBeSummed) {
            return;
        }

        int tempSum = sum + numbers[index];
        sumTo13(index + 1, tempSum);
        tempSum = sum - numbers[index];
        sumTo13(index + 1, tempSum);
    }
}
