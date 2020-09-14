package _3_MoveDownRight;

import java.util.*;

public class MoveDownRight {
    private static int[][] matrix;
    private static int[][] sumMatrix;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();

        in.nextLine();
        matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            String[] line = in.nextLine().split(" ");
            matrix[i] = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
        }

        sumMatrix = new int[row][col];

        moveCalc();
        List<int[]> result = pathMatrix();
        result.forEach(s -> System.out.print(Arrays.toString(s) + " "));
    }

    private static List<int[]> pathMatrix() {
        List<int[]> result = new LinkedList<>();

        int row = sumMatrix.length - 1;
        int col = sumMatrix[row].length - 1;
        result.add(new int[]{row, col});

        while (row != 0 || col != 0) {
            int nextRow = -1;
            if (row - 1 >= 0) {
                nextRow = sumMatrix[row - 1][col];
            }
            int nextCol = -1;
            if (col - 1 >= 0) {
                nextCol = sumMatrix[row][col - 1];
            }
            
            if (nextRow > nextCol) {
                result.add(new int[]{row - 1, col});
                row--;
            } else {
                result.add(new int[]{row, col - 1});
                col--;
            }
        }
        Collections.reverse(result);
        return result;
    }

    private static void moveCalc() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                long maxPrevCell = Long.MIN_VALUE;
                if (col > 0 && sumMatrix[row][col - 1] > maxPrevCell) {
                    maxPrevCell = sumMatrix[row][col - 1];
                }
                if (row > 0 && sumMatrix[row - 1][col] > maxPrevCell) {
                    maxPrevCell = sumMatrix[row - 1][col];
                }

                sumMatrix[row][col] = matrix[row][col];
                if (maxPrevCell != Long.MIN_VALUE) {
                    sumMatrix[row][col] += maxPrevCell;
                }
            }
        }
    }
}
