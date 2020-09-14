package _2_Quicksort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Pivoting with end
public class QuicksortPhi {
    public static int[] numArr;

    public static void main(String[] args) {
//        String input = "5 2 1 4 3";
        String input = "1 2 3 4 5";
        numArr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

//        quickSort(0, numArr.length - 1);
        quickSortDesc(0, numArr.length - 1);
        System.out.println(IntStream.of(numArr).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }

    private static void quickSort(int start, int end) {
        if (start < end) {
            int pivot = pivotPrtition(start, end);

            quickSort(start, pivot - 1);
            quickSort(pivot + 1, end);
        }
    }

    private static int pivotPrtition(int start, int end) {
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

    private static void quickSortDesc(int start, int end) {
        if (start < end) {
            int pivot = pivotPrtitionDesc(start, end);

            quickSortDesc(start, pivot - 1);
            quickSortDesc(pivot + 1, end);
        }
    }

    private static int pivotPrtitionDesc(int start, int end) {
        int pivotValue = numArr[end];

        int smallestIndex = (start - 1);
        for (int i = start; i < end; i++) {
            if (numArr[i] > pivotValue) {
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
