package Lab._1_Blocks;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Blocks {
    private static final int maxSlot = 4;
    private static char[] letter;
    private static final Set<String> rotatedBlock = new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        System.out.println("Number of blocks: " + numberOfBlock(n));
        findBlocksIter(n);
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());

        letter = new char[n];
        for (int i = 0; i < n; i++) {
            letter[i] = (char) ('A' + i);
        }
    }

    private static int numberOfBlock(int n) {
        int blocks = n;

        for (int i = maxSlot - 1; i > 0; --i) {
            blocks *= n - i;
        }

        return blocks / 4;
    }

    private static void findBlocksIter(int n) {
        char lastLetter = (char) ('A' + n - 1);

        for (char l1 = 'A'; l1 <= lastLetter; l1++) {
            for (char l2 = (char) (l1 + 1); l2 <= lastLetter; l2++) {
                for (char l3 = (char) (l1 + 1); l3 <= lastLetter; l3++) {
                    if (l3 != l2) {
                        for (char l4 = (char) (l1 + 1); l4 <= lastLetter; l4++) {
                            if (l4 != l3 && l4 != l2) {
                                System.out.println(CharBuffer.wrap(new char[]{l1, l2, l3, l4}).toString());
                            }
                        }
                    }
                }
            }
        }
    }

    private static Set<String> findBlocks() {
        boolean[] used = new boolean[letter.length];
        char[] combination = new char[maxSlot];
        Set<String> resultCombo = new HashSet<>();

        generateVariation(combination, used, resultCombo, 0);

        return resultCombo;
    }

    private static void generateVariation(char[] combination, boolean[] used, Set<String> resultCombo,
                                          int index) {
        if (index >= combination.length) {
            addBlock(combination, resultCombo);
        } else {
            for (int i = 0; i < letter.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    combination[index] = letter[i];
                    generateVariation(combination, used, resultCombo, index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void addBlock(char[] combination, Set<String> result) {
        String currentCombination = CharBuffer.wrap(combination).toString();
        if (!rotatedBlock.contains(currentCombination)) {
            result.add(currentCombination);

            rotatedBlock.add(currentCombination);
            rotatedBlock.add(CharBuffer.wrap(new char[]{combination[3], combination[0], combination[2], combination[1]}).toString());
            rotatedBlock.add(CharBuffer.wrap(new char[]{combination[2], combination[3], combination[1], combination[0]}).toString());
            rotatedBlock.add(CharBuffer.wrap(new char[]{combination[1], combination[2], combination[0], combination[3]}).toString());
        }
    }
}
