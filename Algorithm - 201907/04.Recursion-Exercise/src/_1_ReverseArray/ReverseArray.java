package _1_ReverseArray;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseArray {
    private static int[] numArr;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        numArr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
//        reverseArray(numArr);
        reverseArray(numArr, 0, numArr.length - 1);
        System.out.println(IntStream.of(numArr).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }

    private static void reverseArray(int[] arr) {
        int j = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, j--);
            if (i == j) {
                break;
            }
        }
    }

    private static void reverseArray(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        swap(arr, start, end);
        reverseArray(arr, start + 1, end - 1);
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
