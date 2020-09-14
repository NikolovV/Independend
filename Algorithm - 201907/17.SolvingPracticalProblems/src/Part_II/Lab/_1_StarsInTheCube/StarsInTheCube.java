package Part_II.Lab._1_StarsInTheCube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class StarsInTheCube {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cubeLayerCount = Integer.parseInt(in.readLine());
        char[][][] cubes = new char[cubeLayerCount][cubeLayerCount][cubeLayerCount];

        for (int i = 0; i < cubeLayerCount; i++) {
            String[] line = in.readLine().split(" \\| ");
            for (int j = 0; j < cubeLayerCount; j++) {
                String[] cells = line[j].split(" ");
                for (int k = 0; k < cubeLayerCount; k++) {
                    cubes[j][i][k] = cells[k].charAt(0);
                }
            }
        }

        Map<Character, Integer> threeDStars = new TreeMap<>();
        for (int layer = 1; layer < cubeLayerCount - 1; layer++) {
            for (int row = 1; row < cubeLayerCount - 1; row++) {
                for (int col = 1; col < cubeLayerCount - 1; col++) {
                    char centerChar = cubes[layer][row][col];

                    if (cubes[layer + 1][row][col] == centerChar &&
                            cubes[layer - 1][row][col] == centerChar &&
                            cubes[layer][row + 1][col] == centerChar &&
                            cubes[layer][row - 1][col] == centerChar &&
                            cubes[layer][row][col + 1] == centerChar &&
                            cubes[layer][row][col - 1] == centerChar) {
                        threeDStars.putIfAbsent(centerChar, 0);
                        threeDStars.put(centerChar, threeDStars.get(centerChar) + 1);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(threeDStars.values().stream().mapToInt(element -> element).sum())
                .append(System.lineSeparator());
        threeDStars.forEach((key, value) -> result.append(String.format("%s -> %d%n", key, value)));
        System.out.print(result);
    }
}
