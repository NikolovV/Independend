package _1_Sorting;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuickSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "5 4 3 2 1"; // in.nextLine();

        int[] numArray = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        quickSort(numArray, 0, numArray.length - 1);

        System.out.println(Arrays.stream(numArray).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivot = pivotPrtition(arr, start, end);

            quickSort(arr, start, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
    }

    private static int pivotPrtition(int[] numArr, int start, int end) {
        int pivotValue = numArr[end];

        int smallestIndex = (start - 1);
        for (int i = start; i < end; i++) {
            if (numArr[i] < pivotValue) {
                swap(numArr, ++smallestIndex, i);
            }
        }
        swap(numArr, smallestIndex + 1, end);
        return smallestIndex + 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
