package _5_CombinationsWithoutRepetition;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CombinationsWithoutRepetition {
    public static int[] pairArray;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int pair = in.nextInt();
        pairArray = new int[pair];

        combinationsWithoutRepetition(pairArray, 0, 1, size);
    }

    public static void combinationsWithoutRepetition(int[] pair, int index, int mark, int elementCount) {
        if (index > pair.length - 1) {
            System.out.println(Arrays.stream(pair).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            return;
        }

        for (int i = mark; i <= elementCount; i++) {
            pair[index] = i;
            combinationsWithoutRepetition(pair, index + 1, mark + 1, elementCount);
            mark++;
        }
    }
}
