package _1_Sorting;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InsertionSort {
    private static int[] numArray;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "5 4 3 2 1"; //in.nextLine();

        numArray = new int[input.split(" ").length];
        fillArray(input);

//        insertionSort(numArray.length);
        insertionSort();

        System.out.println(Arrays.stream(numArray).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

//    private static void insertionSort(int index) {
//        if (index <= 1) {
//            return;
//        }
//        insertionSort(index - 1);
//
//        int lastSorted = numArray[index - 1];
//        int previousIndex = index - 2;
//        while (previousIndex >= 0 && numArray[previousIndex] > lastSorted) {
//            numArray[previousIndex + 1] = numArray[previousIndex];
//            previousIndex--;
//        }
//        numArray[previousIndex + 1] = lastSorted;
//    }

    private static void insertionSort() {
        for (int i = 1; i < numArray.length; ++i) {
            int lastSorted = numArray[i];
            int previosIndex = i - 1;
            while (previosIndex >= 0 && numArray[previosIndex] > lastSorted) {
                numArray[previosIndex + 1] = numArray[previosIndex--];
            }
            numArray[previosIndex + 1] = lastSorted;
        }
    }

    private static void fillArray(String input) {
        numArray = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}


