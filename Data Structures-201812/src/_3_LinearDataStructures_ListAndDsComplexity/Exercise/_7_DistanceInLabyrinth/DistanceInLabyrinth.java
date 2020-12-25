package _3_LinearDataStructures_ListAndDsComplexity.Exercise._7_DistanceInLabyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class DistanceInLabyrinth {
    private static String[][] labyrinth;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(in.readLine());
        labyrinth = new String[size][size];
        int startRow = 0;
        int startCol = 0;

        for (int i = 0; i < size; i++) {
            String[] labyrinthData = in.readLine().split("");

            for (int j = 0; j < labyrinthData.length; j++) {
                if ("*".equals(labyrinthData[j])) {
                    startRow = i;
                    startCol = j;
                }
                labyrinth[i][j] = labyrinthData[j];
            }
        }

        Deque<Cell> queue = new ArrayDeque<>();
        queue.offer(new Cell(startRow, startCol, 0));

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            if (isValidMove(cell.getRow() - 1, cell.getCol())) { // UP
                queue.offer(new Cell(cell.getRow() - 1, cell.getCol(), cell.getStep() + 1));
                labyrinth[cell.getRow() - 1][cell.getCol()] = Integer.toString(cell.getStep() + 1);
            }

            if (isValidMove(cell.getRow(), cell.getCol() + 1)) { // RIGHT
                queue.offer(new Cell(cell.getRow(), cell.getCol() + 1, cell.getStep() + 1));
                labyrinth[cell.getRow()][cell.getCol() + 1] = Integer.toString(cell.getStep() + 1);
            }

            if (isValidMove(cell.getRow() + 1, cell.getCol())) { // DOWN
                queue.offer(new Cell(cell.getRow() + 1, cell.getCol(), cell.getStep() + 1));
                labyrinth[cell.getRow() + 1][cell.getCol()] = Integer.toString(cell.getStep() + 1);
            }

            if (isValidMove(cell.getRow(), cell.getCol() - 1)) { // LEFT
                queue.offer(new Cell(cell.getRow(), cell.getCol() - 1, cell.getStep() + 1));
                labyrinth[cell.getRow()][cell.getCol() - 1] = Integer.toString(cell.getStep() + 1);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int row = 0; row < labyrinth.length; row++) {
            for (int col = 0; col < labyrinth[row].length; col++) {
                if ("0".equals(labyrinth[row][col])) {
                    labyrinth[row][col] = "u";
                }
                result.append(labyrinth[row][col]);
            }
            result.append(System.lineSeparator());
        }
        System.out.print(result);
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && col >= 0
                && row < labyrinth.length && col < labyrinth.length
                && "0".equals(labyrinth[row][col]);
    }

    static class Cell {
        private final int row;
        private final int col;
        private final int step;

        public Cell(int row, int col, int step) {
            this.row = row;
            this.col = col;
            this.step = step;
        }

        int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }

        public int getStep() {
            return this.step;
        }
    }
}
