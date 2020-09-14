package _4_VariationsWithRepetition;

import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VariationsWithRepetition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String input = "a b c";
        char[] elements = in.nextLine().replace(" ", "").toCharArray();

        int slotNum = 2; //in.nextInt();
        char[] slot = new char[slotNum];

        variationsWithRepetition(elements, slot, 0);
    }

    private static void variationsWithRepetition(char[] arr, char[] slot, int index) {
        if (index >= slot.length) {
            System.out.println(CharBuffer.wrap(slot).chars()
                    .mapToObj(inValue -> String.valueOf((char) inValue))
                    .collect(Collectors.joining(" ")));
        } else {
            for (int i = 0; i < arr.length; i++) {
                slot[index] = arr[i];
                variationsWithRepetition(arr, slot, index + 1);
            }
        }
    }
}

