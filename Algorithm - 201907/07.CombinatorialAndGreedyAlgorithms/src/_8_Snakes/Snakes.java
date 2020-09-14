package _8_Snakes;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Snakes {
    private static char[] snake;
    private static final HashSet<String> visited = new LinkedHashSet<>();
    private static final HashSet<String> result = new LinkedHashSet<>();
    private static final HashSet<String> snakeVariation = new LinkedHashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int peaces = in.nextInt();

        snake = new char[peaces];

        generateSnake(0, 0, 0, 'S');
        result.forEach(System.out::println);
        System.out.printf("Snakes count = %d", result.size());
    }

    private static void generateSnake(int index, int row, int col, char direction) {
        if (index >= snake.length) {
            markSnake();
        } else {
            snake[index] = direction;
            String cell = row + " " + col;
            if (!visited.contains(cell)) {
                visited.add(cell);

                generateSnake(index + 1, row, col + 1, 'R');
                generateSnake(index + 1, row + 1, col, 'D');
                generateSnake(index + 1, row, col - 1, 'L');
                generateSnake(index + 1, row - 1, col, 'U');

                visited.remove(cell);
            }
        }
    }

    private static void markSnake() {
        String normalSnake = CharBuffer.wrap(snake).toString();

        if (snakeVariation.contains(normalSnake)) {
            return;
        }
        result.add(normalSnake);
        String flippedSnake = flipSnake(normalSnake);
        String reversedSnake = reversedSnake(normalSnake);
        String reversedFlipSnake = flipSnake(reversedSnake);

        for (int i = 0; i < 4; i++) {
            snakeVariation.add(normalSnake);
            normalSnake = rotateSnake(normalSnake);

            snakeVariation.add(flippedSnake);
            flippedSnake = rotateSnake(flippedSnake);

            snakeVariation.add(reversedSnake);
            reversedSnake = rotateSnake(reversedSnake);

            snakeVariation.add(reversedFlipSnake);
            reversedFlipSnake = rotateSnake(reversedFlipSnake);
        }

    }

    private static String rotateSnake(String normalSnake) {
        char[] rotateSnake = new char[normalSnake.length()];
        for (int i = 0; i < normalSnake.length(); i++) {
            switch (normalSnake.charAt(i)) {
                case 'R':
                    rotateSnake[i] = 'D';
                    break;
                case 'D':
                    rotateSnake[i] = 'L';
                    break;
                case 'L':
                    rotateSnake[i] = 'U';
                    break;
                case 'U':
                    rotateSnake[i] = 'R';
                    break;
                default:
                    rotateSnake[i] = normalSnake.charAt(i);
            }
        }
        return CharBuffer.wrap(rotateSnake).toString();
    }

    private static String reversedSnake(String normalSnake) {
        char[] reversedSnake = new char[normalSnake.length()];
        reversedSnake[0] = 'S';

        for (int i = 1; i < normalSnake.length(); i++) {
            reversedSnake[normalSnake.length() - i] = normalSnake.charAt(i);
        }

        return CharBuffer.wrap(reversedSnake).toString();
    }

    private static String flipSnake(String normalSnake) {
        char[] flipSnake = normalSnake.toCharArray();

        for (int i = 0; i < normalSnake.length(); i++) {
            switch (normalSnake.charAt(i)) {
                case 'U':
                    flipSnake[i] = 'D';
                    break;
                case 'D':
                    flipSnake[i] = 'U';
                    break;
                default:
                    flipSnake[i] = normalSnake.charAt(i);
            }
        }

        return CharBuffer.wrap(flipSnake).toString();
    }

}
