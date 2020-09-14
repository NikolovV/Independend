package Part_I.Exercise._3_Towns;

import java.util.Scanner;

public class Towns {
    private static int townCount;

    public static void main(String[] args) {
        int[] citizenCount = readInput();
        int[] lis = findLongestIncreasingSubsequence(citizenCount);
        reverseArray(citizenCount);
        int[] lds = findLongestIncreasingSubsequence(citizenCount);
        reverseArray(lds);

        System.out.println(longestPath(lis, lds));
    }

    private static int[] readInput() {
        Scanner in = new Scanner(System.in);
        townCount = Integer.parseInt(in.nextLine());

        int[] citizenCount = new int[townCount];
        for (int i = 0; i < townCount; i++) {
            citizenCount[i] = Integer.parseInt(in.nextLine().split(" ")[0]);
        }

        return citizenCount;
    }

    private static int[] findLongestIncreasingSubsequence(int[] number) {
        int[] solutions = new int[number.length];
        int maxSolution = 0;

        for (int i = 0; i < number.length; i++) {
            int solution = 1;
            int currentNumber = number[i];

            for (int j = 0; j < i; j++) {
                int previousNumber = number[j];
                int previousSolution = solutions[j];

                if (currentNumber > previousNumber && solution <= previousSolution) {
                    solution = previousSolution + 1;
                }
            }

            solutions[i] = solution;
            if (solution > maxSolution) {
                maxSolution = solution;
            }
        }

        return solutions;
    }

    private static void reverseArray(int[] number) {
        for (int i = 0; i < number.length / 2; i++) {
            int temp = number[i];
            number[i] = number[number.length - i - 1];
            number[number.length - i - 1] = temp;
        }
    }

    private static int longestPath(int[] secA, int[] secB) {
        int result = 0;
        for (int i = 0; i < townCount; i++) {
            int currentSequence = secA[i] + secB[i];
            if (currentSequence > result) {
                result = currentSequence;
            }
        }
        return result - 1;
    }
}