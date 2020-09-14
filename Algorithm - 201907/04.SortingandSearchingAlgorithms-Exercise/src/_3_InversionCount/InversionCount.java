package _3_InversionCount;

import java.util.Scanner;

public class InversionCount {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");

//        int[] numArray = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int[] sortArray = Arrays.copyOf(numArray, numArray.length);
        int[] numArray = new int[input.length];
        int[] sortArray = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numArray[i] = Integer.parseInt(input[i]);
            sortArray[i] = Integer.parseInt(input[i]);
        }
        System.out.println(mergeInversionCount(numArray, sortArray, 0, numArray.length - 1));
    }

    public static int merge(int[] arr, int[] sortArray, int left, int midddle, int right) {
        int k = left, i = left, j = midddle + 1;
        int inversionCount = 0;

        while (i <= midddle && j <= right) {
            if (arr[i] <= arr[j]) {
                sortArray[k++] = arr[i++];
            } else {
                sortArray[k++] = arr[j++];
                inversionCount += (midddle - i + 1);
            }
        }

        while (i <= midddle) {
            sortArray[k++] = arr[i++];
        }

        for (i = left; i <= right; ++i) {
            arr[i] = sortArray[i];
        }

        return inversionCount;
    }

    public static int mergeInversionCount(int[] arr, int[] sortArray, int left, int right) {
        int inversionCount = 0;
        if (left < right) {
            int mid = (right + left) / 2;
            inversionCount += mergeInversionCount(arr, sortArray, left, mid);
            inversionCount += mergeInversionCount(arr, sortArray, mid + 1, right);

            inversionCount += merge(arr, sortArray, left, mid, right);
        }
        return inversionCount;
    }
}