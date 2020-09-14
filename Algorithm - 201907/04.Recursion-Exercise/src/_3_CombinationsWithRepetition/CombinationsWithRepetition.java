package _3_CombinationsWithRepetition;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CombinationsWithRepetition {
    public static int[] numArr;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int pair = in.nextInt();
        numArr = new int[pair];

        combinationsWithRepetition(numArr, 0, 1, size);
    }

    public static void combinationsWithRepetition(int[] arr, int index, int mark, int elementCount) {
        if (index > arr.length - 1) {
            System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            return;
        }

        for (int i = mark; i <= elementCount; i++) {
            arr[index] = i;
            combinationsWithRepetition(arr, index + 1, mark, elementCount);
            mark++;
        }
    }
}
