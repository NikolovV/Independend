package _3_KnightTour;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Warnsdorff’s algorithm
public class KnightTour {
    private static int[][] desk;

    private static int moves = 1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = 12;//in.nextInt();

        desk = new int[size][size];
        makeTour();

        printKnightTour();
    }

    private static void printKnightTour() {
//        for (int[] ints : desk) {
//            Arrays.stream(ints).forEach(s -> System.out.printf("%3d ", s));
//            System.out.println();
//        }
        Arrays.stream(desk).forEach(row -> {
            Arrays.stream(row).forEach(col -> System.out.printf("%3d ", col));
            System.out.println();
        });
    }

    private static List<Square> findPotentialMoves(Square square) {
        List<Square> result = Arrays.asList(
                new Square(square.row + 1, square.col + 2), // DRR
                new Square(square.row - 1, square.col + 2), // URR
                new Square(square.row + 1, square.col - 2), // DLL
                new Square(square.row - 1, square.col - 2), // ULL
                new Square(square.row + 2, square.col + 1), // DDR
                new Square(square.row + 2, square.col - 1), // DDL
                new Square(square.row - 2, square.col + 1), // UUR
                new Square(square.row - 2, square.col - 1)); // UUR

        return result.stream().filter(s -> isInBound(s.row, s.col) && isEmpty(s)).collect(Collectors.toList());
    }

    private static void makeTour() {
        Square currentSquare = new Square(0, 0);
        List<Square> nextSquareList = findPotentialMoves(currentSquare);
        markPosition(currentSquare);

        while (nextSquareList.size() > 0) {
            currentSquare = minMovesSquare(nextSquareList);
            markPosition(currentSquare);
            nextSquareList = findPotentialMoves(currentSquare);
        }
    }

    private static Square minMovesSquare(List<Square> squareList) {
        Square bestCandidate = null;
        int minNextMoves = Integer.MAX_VALUE;

        for (Square candidate : squareList) {
            int candidateMoves = findPotentialMoves(candidate).size();
            if (candidateMoves < minNextMoves) {
                minNextMoves = candidateMoves;
                bestCandidate = candidate;
            }
        }
        return bestCandidate;
    }

    private static void markPosition(Square square) {
        desk[square.row][square.col] = moves++;
    }

    private static boolean isInBound(int row, int col) {
        return 0 <= row && row < desk.length && 0 <= col && col < desk[0].length;
    }

    private static boolean isEmpty(Square square) {
        return desk[square.row][square.col] == 0;
    }

    public static class Square {
        public int row;
        public int col;

        public Square(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
