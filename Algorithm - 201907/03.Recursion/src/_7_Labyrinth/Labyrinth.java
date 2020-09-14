package _7_Labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Labyrinth {
    private static final char VISITED = 'X';
    private static final char EXIT_MARK = 'e';
    private static final char freeCell = '-';

    private static char[][] labyrinth;

    public static List<Character> pathList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();

        labyrinth = new char[row][col];
        fillLabyrinth(in, row, col);

        traverceLabyrinth(0, 0, 'X');
    }

    public static void traverceLabyrinth(int row, int col, char direction) {
        if (!isInRange(row, col)) {
            return;
        }
        pathList.add(direction);
        if (labyrinth[row][col] == EXIT_MARK) {
            printPath();
        } else if (!isVisited(row, col) && isFree(row, col)) {
            markVisited(row, col);
            traverceLabyrinth(row, col - 1, 'L');
            traverceLabyrinth(row, col + 1, 'R');
            traverceLabyrinth(row - 1, col, 'U');
            traverceLabyrinth(row + 1, col, 'D');
            unMarkVisited(row, col);
        }

        if (pathList.size() > 0) {
            pathList.remove(pathList.size() - 1);
        }
    }

    private static boolean isFree(int row, int col) {
        return labyrinth[row][col] == freeCell;
    }

    private static boolean isVisited(int row, int col) {
        return labyrinth[row][col] == VISITED;
    }

    private static void printPath() {
        if (pathList.indexOf(VISITED) != -1) {
            pathList.remove(pathList.indexOf(VISITED));
        }
        System.out.println(pathList.stream().map(Objects::toString).collect(Collectors.joining("")));
    }

    public static void markVisited(int row, int col) {
        labyrinth[row][col] = VISITED;
    }

    public static void unMarkVisited(int row, int col) {
        labyrinth[row][col] = freeCell;
    }

    public static boolean isInRange(int row, int col) {
        return row < labyrinth.length && col < labyrinth[0].length &&
                row >= 0 && col >= 0;
    }

    public static void fillLabyrinth(Scanner in, int sizeRow, int sizeCol) {
        for (int iRow = 0; iRow < sizeRow; iRow++) {
            String line = in.next();
            for (int iCol = 0; iCol < sizeCol; iCol++) {
                labyrinth[iRow][iCol] = line.charAt(iCol);
            }
        }
    }
}
