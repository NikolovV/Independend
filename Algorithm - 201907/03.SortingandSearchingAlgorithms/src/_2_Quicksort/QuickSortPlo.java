package _2_Quicksort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Pivoting with median
public class QuickSortPlo {
    public static int[] numArr;

    public static void main(String[] args) {
        String input = "3 2 1 5 4";
        numArr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

        quickSort(0, numArr.length - 1);
        System.out.println(IntStream.of(numArr).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }

    private static void quickSort(int start, int end) {
        if (start < end) {
            int pivotIndex = pivotPrtition(start, end);

            quickSort(start, pivotIndex - 1);
            quickSort(pivotIndex + 1, end);
        }
    }

    private static int pivotPrtition(int start, int end) {
        int pivotValue = numArr[start];

        int lastSortIndex = start + 1;

        for (int i = start + 1; i <= end; i++) {
            if (numArr[i] < pivotValue) {
                swap(numArr, i, lastSortIndex++);
            }
        }

        swap(numArr, start, lastSortIndex - 1);
        return lastSortIndex - 1;
    }

    private static <T> void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
