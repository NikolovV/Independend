package _3_VariationsWithoutRepetitions;

import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VariationsWithoutRepetitions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //String input = "a b c";
        char[] elements = in.nextLine().replace(" ", "").toCharArray();
        boolean[] used = new boolean[elements.length];

        int slotNum = in.nextInt();
        char[] slot = new char[slotNum];

        variationWithoutRepetition(elements, slot, used, 0);
    }

    private static void variationWithoutRepetition(char[] arr, char[] slot, boolean[] used, int index) {
        if (index >= slot.length) {
            System.out.println(CharBuffer.wrap(slot).chars()
                    .mapToObj(inValue -> String.valueOf((char) inValue))
                    .collect(Collectors.joining(" ")));
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    slot[index] = arr[i];
                    variationWithoutRepetition(arr, slot, used, index + 1);
                    used[i] = false;
                }
            }
        }
    }
}
