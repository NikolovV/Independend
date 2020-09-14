package _1_Sorting;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BubbleSort {
    private static int[] numArray;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "5 4 3 2 1"; // in.nextLine();

        numArray = new int[input.split(" ").length];
        fillArray(input);

        bubbleSort(numArray.length - 1);

        System.out.println(Arrays.stream(numArray).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void bubbleSort(int size) {
        if (size < 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (numArray[i] > numArray[i + 1]) {
                swap(numArray, i, i + 1);
            }
        }

        bubbleSort(size - 1);
    }

//    private static void bubbleSort() {
//
//        int i, j;
//        for (i = 0; i < numArray.length - 1; i++) {
//            for (j = 0; j < numArray.length - i - 1; j++) {
//                if (numArray[j] > numArray[j + 1]) {
//                    swap(numArray, j, j + 1);
//                }
//            }
//        }
//    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void fillArray(String input) {
        numArray = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}