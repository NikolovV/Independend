package PartIV_Exercise._2_Bridges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bridges {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] north = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] south = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] matrix = new int[north.length + 1][south.length + 1];

        for (int row = 1; row <= north.length; row++) {
            for (int col = 1; col <= south.length; col++) {
                matrix[row][col] = Math.max(matrix[row - 1][col], matrix[row][col - 1]);
                if (north[row - 1] == south[col - 1]) {
                    matrix[row][col]++;
                }
            }
        }

        System.out.println(matrix[north.length][south.length]);
    }
}
