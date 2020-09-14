package _7_NChooseKCount;

import java.util.Scanner;

public class NChooseKCount {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 49; //in.nextInt();
        int k = 6; //in.nextInt();

        System.out.println(binom(n, k));
    }

    private static long binom(int n, int k) {
        if (k > n) {
            return 0;
        }

        if (k == 0 || k == n) {
            return 1;
        }

        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
