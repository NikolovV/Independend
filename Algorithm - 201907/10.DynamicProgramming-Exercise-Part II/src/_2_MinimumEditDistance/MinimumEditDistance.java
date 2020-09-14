package _2_MinimumEditDistance;

import java.util.Scanner;

public class MinimumEditDistance {
    private static int replace;
    private static int insert;
    private static int delete;

    private static String s1;
    private static String s2;
    private static int[][] costSol;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        replace = /*5;//*/ Integer.parseInt(in.nextLine().substring(15));
        insert = /*2;//*/ Integer.parseInt(in.nextLine().substring(14));
        delete = /*1;//*/ Integer.parseInt(in.nextLine().substring(14));

        s1 = /*"nqma bira";// */in.nextLine().substring(5);
        s2 = /*"ima bira";//*/in.nextLine().substring(5);

        if (s1.compareTo(s2) == 0) {
            System.out.println("Minimum edit distance: 0");
        } else {
            System.out.println("Minimum edit distance: " + minimumEditDistanceCost());

            for (int i = 0; i < costSol.length; i++) {
                for (int j = 0; j < costSol[0].length; j++) {
                    System.out.print(costSol[i][j] + "\t");
                }
                System.out.println();
            }

            printOperation(s1.length(), s2.length());
        }
    }

    private static int minimumEditDistanceCost() {
        costSol = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            costSol[i][0] = costSol[i - 1][0] + delete;
        }
        for (int i = 1; i <= s2.length(); i++) {
            costSol[0][i] = costSol[0][i - 1] + insert;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    costSol[i][j] = costSol[i - 1][j - 1];
                } else {
                    costSol[i][j] = minimum(costSol[i - 1][j] + delete
                            , costSol[i][j - 1] + insert,
                            costSol[i - 1][j - 1] + replace);
                }
            }
        }

        return costSol[s1.length()][s2.length()];
    }

    private static void printOperation(int i, int j) {
        if (j == 0) {
            for (; j < i; j++) {
                System.out.printf("DELETE(%d)\n", j);
            }
        } else if (i == 0) {
            for (; i < j; i++) {
                System.out.printf("INSERT(%d, %c)\n", i, s2.charAt(i));
            }
        } else if (i > 0 && j > 0) {
            if (costSol[i][j] == costSol[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : replace)) {
                printOperation(i - 1, j - 1);
                if ((s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : replace) > 0) {
                    System.out.printf("REPLACE(%d, %c)\n", i - 1, s2.charAt(j - 1));
                }
            } else if (costSol[i][j] == costSol[i][j - 1] + insert) {
                printOperation(i, j - 1);
                System.out.printf("INSERT(%d, %c)\n", j, s2.charAt(j - 1));
            } else if (costSol[i][j] == costSol[i - 1][j] + delete) {
                printOperation(i - 1, j);
                System.out.printf("DELETE(%d)\n", i - 1);
            }
        }
    }

    private static int minimum(int a, int b, int c) {
        return Integer.min(a, Integer.min(b, c));
    }
}
