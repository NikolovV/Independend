package _1_BinomialCoefficients;

import java.util.Scanner;

public class BinomialCoefficients {
    private static long[][] cahsedBinom;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        cahsedBinom = new long[n + 1][k + 1];

        System.out.println(binom(n, k));
    }

    private static long binom(int n, int k) {
        if (k > n) {
            return 0;
        } else if (k == 0 || k == n) {
            cahsedBinom[n][k] = 1;
            return 1;
        }

        if (cahsedBinom[n][k] == 0) {
            cahsedBinom[n][k] = binom(n - 1, k - 1) + binom(n - 1, k);
        }
        return cahsedBinom[n][k];
    }
}
