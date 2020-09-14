package _3_SymbolMultiplication;

import java.util.Arrays;
import java.util.Scanner;

public class SymbolMultiplication {
    private static char[] alphabet;
    private static char[][] table;
    private static String str;
    private static byte[][][] dpMemorisation;
    private static byte[][] split;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        alphabet = in.nextLine().substring(12).replaceAll("[ , }]", "").toCharArray();

        table = new char[alphabet.length][alphabet.length];
        in.nextLine();
        for (int i = 0; i < alphabet.length; i++) {
            table[i] = in.nextLine().toCharArray();
        }

        str = in.nextLine().substring(4);
        dpMemorisation = new byte[str.length()][str.length()][alphabet.length];
        for (int i = 0; i < dpMemorisation.length; i++) {
            for (int j = 0; j < dpMemorisation[i].length; j++) {
                Arrays.fill(dpMemorisation[i][j], (byte) -1);
            }
        }
        split = new byte[str.length()][str.length()];
        if (symbolMultiplication((byte) 0, (byte) (str.length() - 1), (byte) 0) == 1) {
            putBracket((byte) 0, (byte) (str.length() - 1));
        } else {
            System.out.print("No solution\n");
        }
    }

    public static byte symbolMultiplication(byte i, byte j, byte ch) {
        byte c1, c2, pos;
        if (dpMemorisation[i][j][ch] != -1) {
            return dpMemorisation[i][j][ch];
        }
        if (i == j) {
            return (byte) ((str.charAt(i) == ch + 'a') ? 1 : 0);
        }
        for (c1 = 0; c1 < alphabet.length; c1++) {
            for (c2 = 0; c2 < alphabet.length; c2++) {
                if (table[c1][c2] == ch + 'a') {
                    for (pos = i; pos <= j - 1; pos++) {
                        if (symbolMultiplication(i, pos, c1) == 1) {
                            if (symbolMultiplication((byte) (pos + 1), j, c2) == 1) {
                                dpMemorisation[i][j][ch] = 1;
                                split[i][j] = pos;
                                return 1;
                            }
                        }
                    }
                }
            }
        }
        dpMemorisation[i][j][ch] = 0;
        return 0;
    }

    private static void putBracket(byte a, byte b) {
        if (a == b) {
            System.out.printf("%c", str.charAt(a));
        } else {
            System.out.print("(");
            putBracket(a, split[a][b]);
            System.out.print("*");
            putBracket((byte) (split[a][b] + 1), b);
            System.out.print(")");
        }
    }
}
