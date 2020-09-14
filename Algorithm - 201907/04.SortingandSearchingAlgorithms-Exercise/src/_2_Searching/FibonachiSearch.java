package _2_Searching;

import java.util.Arrays;
import java.util.Scanner;

public class FibonachiSearch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] numArray = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int searchValue = in.nextInt();

        System.out.println(fibonachiSearch(numArray, searchValue, numArray.length));
    }

    private static int fibonachiSearch(int[] arr, int searchVal, int size) {
        int fibMin = 0;
        int fibNext = 1;
        int fibM = fibMin + fibNext;

        while (fibM < size) {
            fibMin = fibNext;
            fibNext = fibM;
            fibM = fibMin + fibNext;
        }

        int offset = -1;

        while (fibM > 1) {
            int i = Math.min(offset + fibMin, size - 1);

            if (arr[i] < searchVal) {
                fibM = fibNext;
                fibNext = fibMin;
                fibMin = fibM - fibNext;
                offset = i;
            } else if (arr[i] > searchVal) {
                fibM = fibMin;
                fibNext = fibNext - fibMin;
                fibMin = fibM - fibNext;
            } else {
                return i;
            }
        }

        if (fibNext == 1 && arr[offset + 1] == searchVal) {
            return offset + 1;
        }

        return -1;
    }
}
