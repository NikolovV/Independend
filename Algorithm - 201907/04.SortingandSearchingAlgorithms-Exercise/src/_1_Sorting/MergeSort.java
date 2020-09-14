package _1_Sorting;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergeSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        String input = in.nextLine().split(" ");
//        int[] numArray = new int[input.length];
//        for (int i = 0; i < input.length; i++) {
//            numArray[i] = Integer.parseInt(input[i]);
//        }

        int[] numArray = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //        fillArray(input);
        mergeSort(numArray, 0, numArray.length - 1);

        System.out.println(Arrays.stream(numArray).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int median = (start + end) / 2;

            mergeSort(arr, start, median);
            mergeSort(arr, median + 1, end);

            merge(arr, start, median, end);
        }
    }

    public static void merge(int[] numArray, int left, int middle, int right) {
        int leftStart = left, rightStart = middle + 1;

        int[] tempArr = new int[right - left + 1];
        int tempIndex = 0;

        for (int i = left; i <= right; i++) {
            if (leftStart > middle) {
                tempArr[tempIndex++] = numArray[rightStart++];
            } else if (rightStart > right) {
                tempArr[tempIndex++] = numArray[leftStart++];
            } else if (numArray[leftStart] < numArray[rightStart]) {
                tempArr[tempIndex++] = numArray[leftStart++];
            } else {
                tempArr[tempIndex++] = numArray[rightStart++];
            }
        }

        for (int p = 0; p < tempIndex; p++) {
            numArray[left++] = tempArr[p];
        }
    }

    //    private static void fillArray(String input) {
    //        numArray = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    //    }
}
