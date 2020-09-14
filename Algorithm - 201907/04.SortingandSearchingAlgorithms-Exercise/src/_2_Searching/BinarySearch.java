package _2_Searching;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

//        Integer[] numArray = Arrays.stream(Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray()).boxed().toArray(Integer[]::new);
//        Integer[] numArray = IntStream.of(Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray()).boxed().toArray(Integer[]::new);
        int[] numArray = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int searchValue = in.nextInt();

        System.out.println(binarySearch(numArray, searchValue));
    }

    private static int binarySearch(int[] arr, int value) {
        if (value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        } else {
            return binarySearch(arr, 0, arr.length, value);
        }
    }

    private static int binarySearch(int[] arr, int start, int end, int value) {
        int mid = (start + end) / 2;
        if (value == arr[mid]) {
            return mid;
        }

        if (value < arr[mid]) {
            return binarySearch(arr, start, mid, value);
        } else {
            return binarySearch(arr, mid, end, value);
        }
    }
}
