package _1_MergeSort;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSort {
    public static int[] numArr;

    public static void main(String[] args) {
        String input = "5 4 3 2 1";

        numArr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

        mergeSort(0, numArr.length - 1);

        System.out.println(IntStream.of(numArr).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }

    public static void mergeSort(int start, int end) {
        if (start < end) {
            int median = (start + end) / 2;

            mergeSort(start, median);
            mergeSort(median + 1, end);

            merge(start, median, end);
        }
    }

    public static void merge(int left, int middle, int right) {
        int leftStart = left, rightStart = middle + 1;

        int[] tempArr = new int[right - left + 1];
        int tempIndex = 0;

        for (int i = left; i <= right; i++) {
            if (leftStart > middle) {
                tempArr[tempIndex++] = numArr[rightStart++];
            } else if (rightStart > right) {
                tempArr[tempIndex++] = numArr[leftStart++];
            } else if (numArr[leftStart] < numArr[rightStart]) {
                tempArr[tempIndex++] = numArr[leftStart++];
            } else {
                tempArr[tempIndex++] = numArr[rightStart++];
            }
        }

        for (int p = 0; p < tempIndex; p++) {
            numArr[left++] = tempArr[p];
        }
    }
}
