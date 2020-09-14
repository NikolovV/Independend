package _2_LongestZigzagSubsequence;

import java.util.*;
import java.util.stream.Collectors;

public class LongestZigzagSubsequence {
    private static int[] numbers;
    private static int[][] cashed;
    private static int[][] restore;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        numbers = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cashed = new int[numbers.length][2];
        restore = new int[numbers.length][2];
        cashed[0][0] = cashed[0][1] = 1;
        restore[0][0] = restore[0][1] = -1;

        longestZigzagSubsequence();
    }

    private static void longestZigzagSubsequence() {
        int lzs = 0;
        int maxRow = 0;
        int maxCol = 0;

        for (int currentIndex = 1; currentIndex < numbers.length; currentIndex++) {
            for (int prevIndex = 0; prevIndex < currentIndex; prevIndex++) {
                int currentNumber = numbers[currentIndex];
                int prevNumber = numbers[prevIndex];
                if (currentNumber > prevNumber && cashed[currentIndex][0] < cashed[prevIndex][1] + 1) {
                    cashed[currentIndex][0] = cashed[prevIndex][1] + 1;
                    restore[currentIndex][0] = prevIndex;
                }
                if (currentNumber < prevNumber && cashed[currentIndex][1] < cashed[prevIndex][0] + 1) {
                    cashed[currentIndex][1] = cashed[prevIndex][0] + 1;
                    restore[currentIndex][1] = prevIndex;
                }
            }

            if (cashed[currentIndex][0] > lzs) {
                lzs = cashed[currentIndex][0];
                maxRow = currentIndex;
                maxCol = 0;
            }
            if (cashed[currentIndex][1] > lzs) {
                lzs = cashed[currentIndex][1];
                maxRow = currentIndex;
                maxCol = 1;
            }
        }

        List<Integer> result = new ArrayList<>();

        while (maxRow >= 0) {
            result.add(numbers[maxRow]);
            maxRow = restore[maxRow][maxCol];
            if (maxCol == 1) {
                maxCol = 0;
            } else {
                maxCol = 1;
            }
        }

        Collections.reverse(result);
        System.out.println(Arrays.stream(result.toArray()).map(String::valueOf).collect(Collectors.joining(" ")));
    }

}
