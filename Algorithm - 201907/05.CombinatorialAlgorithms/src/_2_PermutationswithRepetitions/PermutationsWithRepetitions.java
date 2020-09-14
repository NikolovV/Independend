package _2_PermutationswithRepetitions;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PermutationsWithRepetitions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String input = "a b b";
        char[] elements = input.replace(" ", "").toCharArray();
//        char[] elements = in.nextLine().replace(" ", "").toCharArray();

        Arrays.sort(elements);
        permute(elements, 0, elements.length - 1);
    }

    private static void permute(char[] arr, int start, int end) {
        System.out.println(CharBuffer.wrap(arr).chars()
                .mapToObj(inValue -> String.valueOf((char) inValue))
                .collect(Collectors.joining(" ")));

        for (int left = end - 1; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                if (arr[left] != arr[right]) {
                    swap(arr, left, right);
                    permute(arr, left + 1, end);
                }
            }
            char firstElement = arr[left];
            for (int i = left; i <= end - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[end] = firstElement;
        }
    }

    private static void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
