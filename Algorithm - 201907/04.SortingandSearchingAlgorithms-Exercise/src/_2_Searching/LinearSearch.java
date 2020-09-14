package _2_Searching;

import java.util.Arrays;
import java.util.Scanner;

public class LinearSearch {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "1 2 3 4 5"; // in.nextLine();
        int saerchVal = 6; //in.nextInt();

        int[] numArray = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(linear(numArray, saerchVal));
    }

    private static int linear(int[] arr, int searchVal) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == searchVal) {
                return i;
            }
        }

        return -1;
    }
}
