package _6_8QueensPuzzle;

public class EightQueensPuzzle_1 {
    final static int SIZE = 8;
    static int[] desk = new int[SIZE];

    public static void main(String[] args) {
        genrateEightQueensPuzzle(0);
    }

    public static void genrateEightQueensPuzzle(int row) {
        if (row == SIZE) {
            printDesk();
        } else {
            for (int col = 0; col < SIZE; col++) {
                desk[row] = col;
                if (canPutQueen(row)) {
                    genrateEightQueensPuzzle(row + 1);
                }
            }
        }
    }

    public static boolean canPutQueen(int row) {
        for (int col = 0; col < row; col++) {
            if (desk[col] == desk[row]) {
                return false;   // same column
            }
            if ((desk[col] - desk[row]) == (row - col)) {
                return false;   // same major diagonal
            }
            if ((desk[row] - desk[col]) == (row - col)) {
                return false;   // same minor diagonal
            }
        }
        return true;
    }

    public static void printDesk() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(desk[row] == col ? "*" : "-");
                if (col < SIZE) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
