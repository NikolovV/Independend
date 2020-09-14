package Part_II.Lab._3_ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class ShortestPath {
    private static StringBuilder path;
    private static int[] crossroadIndexes;
    private static char[] pathCombination;
    private static int pathVariationCount;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] map = in.readLine().toCharArray();

        crossroadIndexes = IntStream.range(0, map.length).filter((x) -> map[x] == '*').toArray();

        if (crossroadIndexes.length == 0) {
            System.out.println(1);
            System.out.println(map);
            return;
        }

        path = new StringBuilder();

        pathCombination = new char[crossroadIndexes.length];
        findPath(map, 0);

        System.out.println(pathVariationCount);
        System.out.println(path);
    }

    private static void findPath(char[] map, int index) {
        if (index == crossroadIndexes.length) {
            pathVariationCount++;
            for (int i = 0; i < crossroadIndexes.length; i++) {
                int currentIndex = crossroadIndexes[i];
                map[currentIndex] = pathCombination[i];
            }

            path.append(map).append(System.lineSeparator());
            return;
        }

        pathCombination[index] = 'L';
        findPath(map, index + 1);
        pathCombination[index] = 'R';
        findPath(map, index + 1);
        pathCombination[index] = 'S';
        findPath(map, index + 1);
    }
}