package _2_LongestCommonSubsequence;

import java.util.Scanner;

public class LongestCommonSubsequence {
    private static int[][] lcs;
    private static String s1;
    private static String s2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        s1 = in.nextLine();
        s2 = in.nextLine();

        lcs = new int[s1.length() + 1][s2.length() + 1];

        for (int row = 1; row <= s1.length(); row++) {
            for (int col = 1; col <= s2.length(); col++) {
                int up = lcs[row - 1][col];
                int left = lcs[row][col - 1];

                int max = Math.max(up, left);
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    max = Math.max(1 + lcs[row - 1][col - 1], max);
                }
                lcs[row][col] = max;
            }
        }

        System.out.println(lcs[s1.length()][s2.length()]);
    }

}
