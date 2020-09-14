package GraphsAndGraphAlgorithms._2_AreasInMatrix;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AreasInMatrix {
    private static char[][] matrix;
    private static boolean[][] visited;
    private static Map<Character, Integer> resultMap = new TreeMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        matrix = new char[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = in.nextLine().toCharArray();
        }
        visited = new boolean[matrix.length][matrix[0].length];

        areaInMatrix();

        System.out.println("Areas: " + resultMap.values().stream().reduce(0, Integer::sum));
        resultMap.forEach((key, value) -> System.out.printf("Letter '%c' -> %d\n", key, value));
    }

    private static void areaInMatrix() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!visited[row][col]) {
                    if (!resultMap.containsKey(matrix[row][col])) {
                        resultMap.put(matrix[row][col], 0);
                    }
                    dfs(row, col);
                    resultMap.put(matrix[row][col], resultMap.get(matrix[row][col]) + 1);
                }
            }
        }
    }

    private static void dfs(int row, int col) {
        if (isInRange(row, col)) {
            if (!visited[row][col]) {
                visited[row][col] = true;
                if (isChild(row, col, row, col + 1)) { // R
                    dfs(row, col + 1);
                }
                if (isChild(row, col, row + 1, col)) { // D
                    dfs(row + 1, col);
                }
                if (isChild(row, col, row, col - 1)) { // L
                    dfs(row, col - 1);
                }
                if (isChild(row, col, row - 1, col)) { // U
                    dfs(row - 1, col);
                }
            }
        }
    }

    private static boolean isChild(int parRow, int parCol, int childRow, int childCol) {
        return isInRange(childRow, childCol) && matrix[parRow][parCol] == matrix[childRow][childCol];
    }

    private static boolean isInRange(int row, int col) {
        return (row < matrix.length && row >= 0 && col < matrix[row].length && col >= 0);
    }
}
