package _6_CombinationsWithRepetition;

import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CombinationsWithRepetition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String input = "a b c";
        char[] elements = in.nextLine().replace(" ", "").toCharArray();

        int slotNum = in.nextInt();
        char[] slot = new char[slotNum];

        combinationsWithRepetition(elements, slot, 0, 0);
    }

    private static void combinationsWithRepetition(char[] arr, char[] slot, int index, int mark) {
        if (index >= slot.length) {
            System.out.println(CharBuffer.wrap(slot).chars()
                    .mapToObj(inValue -> String.valueOf((char) inValue))
                    .collect(Collectors.joining(" ")));
        } else {
            for (int i = mark; i < arr.length; i++) {
                slot[index] = arr[i];
                combinationsWithRepetition(arr, slot, index + 1, i);
            }
        }
    }
}
