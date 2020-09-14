package _1_Sorting;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShellSort {
    private static int[] numArray;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "5 4 3 2 1"; // in.nextLine();

        numArray = new int[input.split(" ").length];
        fillArray(input);

        shellSort(numArray.length);

        System.out.println(Arrays.stream(numArray).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void shellSort(int size) {
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; ++i) {
                int temp = numArray[i];
                int j;
                for (j = i; j >= gap && numArray[j - gap] > temp; j -= gap) {
                    numArray[j] = numArray[j - gap];
                }
                numArray[j] = temp;
            }
        }
    }

    private static void fillArray(String input) {
        numArray = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

