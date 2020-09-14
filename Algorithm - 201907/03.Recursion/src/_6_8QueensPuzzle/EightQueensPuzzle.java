package _6_8QueensPuzzle;

import java.util.HashSet;

public class EightQueensPuzzle {

    final static int SIZE = 8;
    static boolean[][] desk = new boolean[SIZE][SIZE];

    static HashSet<Integer> attackedCol = new HashSet<>();
    static HashSet<Integer> attackedLeftDiagonal = new HashSet<>();
    static HashSet<Integer> attackedRightDiagonal = new HashSet<>();

    public static void main(String[] args) {
        genrateEightQueensPuzzle(0);
    }

    public static void genrateEightQueensPuzzle(int row) {
        if (row == SIZE) {
            printDesk();
        } else {
            for (int col = 0; col < SIZE; col++) {
                if (canPutQueen(row, col)) {
                    markAttackPositions(row, col);
                    genrateEightQueensPuzzle(row + 1);
                    unmarkAttackPositions(row, col);
                }
            }
        }
    }

    public static void unmarkAttackPositions(int row, int col) {
        attackedCol.remove(col);
        attackedLeftDiagonal.remove(col - row);
        attackedRightDiagonal.remove(col + row);
        desk[row][col] = false;
    }

    public static void markAttackPositions(int row, int col) {
        attackedCol.add(col);
        attackedLeftDiagonal.add(col - row);
        attackedRightDiagonal.add(col + row);
        desk[row][col] = true;
    }

    public static boolean canPutQueen(int row, int col) {

        return !(attackedCol.contains(col) ||
                attackedLeftDiagonal.contains(col - row) ||
                attackedRightDiagonal.contains(col + row));
    }

    public static void printDesk() {
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                System.out.print(desk[i][j] ? "*" : "-");
                if (j < SIZE) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
