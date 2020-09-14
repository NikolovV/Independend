package _1_PermutationswithoutRepetitions;

import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PermutationswithoutRepetitions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String input = "A B C";
        char[] elements = input.replace(" ", "").toCharArray();
        char[] permute = new char[elements.length];
        boolean[] used = new boolean[elements.length];

//        permutate(elements, used, permute, 0);
        permutate(elements, 0);
    }

    private static void permutate(char[] arr, boolean[] used, char[] permute, int index) {
        if (index >= arr.length) {
            System.out.println(CharBuffer.wrap(permute).chars()
                    .mapToObj(inValue -> String.valueOf((char) inValue))
                    .collect(Collectors.joining(" ")));
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    permute[index] = arr[i];
                    permutate(arr, used, permute, index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void permutate(char[] arr, int index) {
        if (index >= arr.length) {
            System.out.println(CharBuffer.wrap(arr).chars()
                    .mapToObj(inValue -> String.valueOf((char) inValue))
                    .collect(Collectors.joining(" ")));
        } else {
            permutate(arr, index + 1);
            for (int i = index + 1; i < arr.length; i++) {
                swap(arr, i, index);
                permutate(arr, index + 1);
                swap(arr, index, i);
            }
        }

    }

    private static void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
