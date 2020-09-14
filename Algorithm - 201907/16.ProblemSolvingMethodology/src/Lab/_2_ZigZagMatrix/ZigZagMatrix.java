package Lab._2_ZigZagMatrix;

import java.util.*;
import java.util.stream.Collectors;

public class ZigZagMatrix {
    private static int rowsCount;
    private static int colsCount;
    private static int[][] matrix;
    private static int[][] maxPath;
    private static int[][] prevIndex;

    public static void main(String[] args) {
        parseInput();
        findMaxPath();
        int currentRowIndex = getLastRowIndexOfPath();
        List<Integer> path = recoverPath(currentRowIndex);

        System.out.print(path.stream().mapToInt(Integer::intValue).sum());
        System.out.print(" = ");
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" + ")));
    }

    private static List<Integer> recoverPath(int currentRowIndex) {
        List<Integer> path = new ArrayList<>();
        int columnIndex = colsCount - 1;

        while (columnIndex >= 0) {
            path.add(matrix[currentRowIndex][columnIndex]);
            currentRowIndex = prevIndex[currentRowIndex][columnIndex];
            columnIndex--;
        }
        Collections.reverse(path);
        return path;
    }

    private static int getLastRowIndexOfPath() {
        int currentRowIndex = -1;
        int globalMax = 0;
        for (int row = 0; row < maxPath.length; row++) {
            if (maxPath[row][colsCount - 1] > globalMax) {
                globalMax = maxPath[row][colsCount - 1];
                currentRowIndex = row;
            }
        }

        return currentRowIndex;
    }

    private static void findMaxPath() {
        for (int col = 1; col < colsCount; col++) {
            for (int row = 0; row < rowsCount; row++) {
                int prevMaxValue = 0;
                if (col % 2 != 0) {
                    for (int i = row + 1; i < rowsCount; i++) {
                        if (maxPath[i][col - 1] > prevMaxValue) {
                            prevMaxValue = maxPath[i][col - 1];
                            prevIndex[row][col] = i;
                        }
                    }
                } else {
                    for (int i = 0; i < row; i++) {
                        if (maxPath[i][col - 1] > prevMaxValue) {
                            prevMaxValue = maxPath[i][col - 1];
                            prevIndex[row][col] = i;
                        }
                    }
                }
                maxPath[row][col] = prevMaxValue + matrix[row][col];
            }
        }
    }

    private static void parseInput() {
        Scanner in = new Scanner(System.in);

        rowsCount = Integer.parseInt(in.nextLine());
        colsCount = Integer.parseInt(in.nextLine());

        matrix = new int[rowsCount][colsCount];

        for (int i = 0; i < rowsCount; i++) {
            matrix[i] = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        maxPath = new int[rowsCount][colsCount];
        prevIndex = new int[rowsCount][colsCount];

        for (int i = 1; i < rowsCount; i++) {
            maxPath[i][0] = matrix[i][0];
        }

    }
}
