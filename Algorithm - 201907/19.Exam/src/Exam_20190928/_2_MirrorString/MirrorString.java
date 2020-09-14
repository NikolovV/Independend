package Exam_20190928._2_MirrorString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MirrorString {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();
        String rev = new StringBuilder(str).reverse().toString();

        System.out.println(lcs(str, rev));
    }

    private static String lcs(String a, String b) {
        int firstStrLength = a.length();
        int secondStrLength = b.length();
        char[] firstStr = a.toCharArray();
        char[] secondStr = b.toCharArray();

        int[][] lcsDP = new int[firstStrLength + 1][secondStrLength + 1];

        for (int firstStrIndex = 0; firstStrIndex <= firstStrLength; firstStrIndex++) {
            for (int secondStrIndex = 0; secondStrIndex <= secondStrLength; secondStrIndex++) {
                if (firstStrIndex == 0 || secondStrIndex == 0) {
                    lcsDP[firstStrIndex][secondStrIndex] = 0;
                } else if (firstStr[firstStrIndex - 1] == secondStr[secondStrIndex - 1]) {
                    lcsDP[firstStrIndex][secondStrIndex] = lcsDP[firstStrIndex - 1][secondStrIndex - 1] + 1;
                } else {
                    lcsDP[firstStrIndex][secondStrIndex] = Math.max(lcsDP[firstStrIndex - 1][secondStrIndex], lcsDP[firstStrIndex][secondStrIndex - 1]);
                }
            }
        }

        int index = lcsDP[firstStrLength][secondStrLength];

        char[] lcsResult = new char[index + 1];

        int i = firstStrLength, j = secondStrLength;
        while (i > 0 && j > 0) {
            if (firstStr[i - 1] == secondStr[j - 1]) {
                lcsResult[index - 1] = firstStr[i - 1];
                i--;
                j--;
                index--;
            } else if (lcsDP[i - 1][j] > lcsDP[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcsResult);
    }

}
