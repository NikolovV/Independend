package _3_BinarySearch;

import java.util.Scanner;

public class BinarySearch<T extends Comparable<T>> {
    private static final Integer[] numArr = new Integer[]{-2, -1, 0, 1, 2, 3, 4, 5};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int searchValue = in.nextInt();
        System.out.println(String.format("Index of %d is: %s", searchValue,
                binarySearch(numArr, searchValue) == -1 ? "no such element" : Integer.toString(binarySearch(numArr, searchValue))));
    }

    private static <T> int binarySearch(T[] arr, T value) {
        if (value.hashCode() < arr[0].hashCode() || value.hashCode() > arr[arr.length - 1].hashCode()) {
            return -1;
        } else {
            return binarySearch(arr, 0, arr.length, value);
        }
    }

    private static <T> int binarySearch(T[] arr, int start, int end, T value) {
        int mid = (start + end) / 2;
        if (value == arr[mid]) {
            return mid;
        }

        if (value.hashCode() < arr[mid].hashCode()) {
            return binarySearch(arr, start, mid, value);
        } else {
            return binarySearch(arr, mid, end, value);
        }
    }
}
