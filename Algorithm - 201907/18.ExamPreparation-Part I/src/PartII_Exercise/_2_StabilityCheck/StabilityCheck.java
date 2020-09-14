package PartII_Exercise._2_StabilityCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StabilityCheck {
    private static int buildingSize;
    private static int matrixSize;
    private static long[][] sumMapLand;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        buildingSize = Integer.parseInt(in.readLine());
        matrixSize = Integer.parseInt(in.readLine());
        sumMapLand = new long[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            int[] mapData = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < mapData.length; j++) {

                if (i == 0 && j == 0) {
                    sumMapLand[i][j] = mapData[j];
                } else if (i == 0) {
                    sumMapLand[i][j] = mapData[j] + sumMapLand[i][j - 1];
                } else if (j == 0) {
                    sumMapLand[i][j] = mapData[j] + sumMapLand[i - 1][j];
                } else {
                    sumMapLand[i][j] = mapData[j] + sumMapLand[i][j - 1] + sumMapLand[i - 1][j] - sumMapLand[i - 1][j - 1];
                }
            }
        }

        long maxSum = getMaxSum();
        System.out.print(maxSum);
    }

    private static long getMaxSum() {
        int roof = matrixSize - buildingSize;
        long maxSum = Long.MIN_VALUE;

        for (int i = 0; i <= roof; i++) {
            for (int j = 0; j <= roof; j++) {
                maxSum = Math.max(maxSum, calculateSum(i, j, i + buildingSize - 1, j + buildingSize - 1));
            }
        }

        return maxSum;
    }

    private static long calculateSum(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == 0 && startCol == 0) {
            return sumMapLand[endRow][endCol];
        } else if (startRow == 0) {
            return sumMapLand[endRow][endCol] - sumMapLand[endRow][startCol - 1];
        } else if (startCol == 0) {
            return sumMapLand[endRow][endCol] - sumMapLand[startRow - 1][endCol];
        } else {
            return sumMapLand[endRow][endCol] - sumMapLand[endRow][startCol - 1] - sumMapLand[startRow - 1][endCol] + sumMapLand[startRow - 1][startCol - 1];
        }
    }
}
