package Exam_20190928._3_RoadTrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RoadTrip {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] values = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] weights = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int capacity = Integer.parseInt(reader.readLine());

        System.out.printf("Maximum value: %d", dp(capacity, weights, values, values.length));
    }

    private static int dp(int capacity, int[] weights, int[] values, int size) {
        int[][] memory = new int[size + 1][capacity + 1];

        for (int itemIndex = 0; itemIndex <= size; itemIndex++) {
            for (int capacityIndex = 0; capacityIndex <= capacity; capacityIndex++) {
                if (itemIndex == 0 || capacityIndex == 0) {
                    memory[itemIndex][capacityIndex] = 0;
                } else if (weights[itemIndex - 1] <= capacityIndex) {
                    memory[itemIndex][capacityIndex] =
                            Math.max(values[itemIndex - 1] + memory[itemIndex - 1][capacityIndex - weights[itemIndex - 1]],
                                    memory[itemIndex - 1][capacityIndex]);
                } else {
                    memory[itemIndex][capacityIndex] = memory[itemIndex - 1][capacityIndex];
                }
            }
        }

        return memory[size][capacity];
    }
}
