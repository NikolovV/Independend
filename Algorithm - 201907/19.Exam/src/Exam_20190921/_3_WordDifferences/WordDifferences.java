package Exam_20190921._3_WordDifferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordDifferences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String first = reader.readLine();
        String second = reader.readLine();

        int operations = editDistDP(first, second);

        System.out.printf("Deletions and Insertions: %d%n", operations);
    }

    private static int editDistDP(String first, String second) {
        int[][] dp = new int[first.length() + 1][second.length() + 1];
        int fistLength = first.length();
        int secondLength = second.length();

        for (int firstStrIndex = 0; firstStrIndex <= fistLength; firstStrIndex++) {
            for (int secondStrIndex = 0; secondStrIndex <= secondLength; secondStrIndex++) {
                if (firstStrIndex == 0) {
                    dp[firstStrIndex][secondStrIndex] = secondStrIndex;
                } else if (secondStrIndex == 0) {
                    dp[firstStrIndex][secondStrIndex] = firstStrIndex;
                } else if (first.charAt(firstStrIndex - 1) == second.charAt(secondStrIndex - 1)) {
                    dp[firstStrIndex][secondStrIndex] = dp[firstStrIndex - 1][secondStrIndex - 1];
                } else {
                    dp[firstStrIndex][secondStrIndex] = 1 + Math.min(dp[firstStrIndex][secondStrIndex - 1], dp[firstStrIndex - 1][secondStrIndex]);
                }
            }
        }

        return dp[fistLength][secondLength];
    }
}
