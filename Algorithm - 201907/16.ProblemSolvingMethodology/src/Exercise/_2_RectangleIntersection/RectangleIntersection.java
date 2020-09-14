package Exercise._2_RectangleIntersection;

import java.io.IOException;
import java.util.Scanner;

public class RectangleIntersection {
    public static void main(String[] args) throws IOException {
        int[][] plane = parseInput();
        int totalIntersectionArea = calcTotalArea(plane);
        System.out.println(totalIntersectionArea);
    }

    private static int[][] parseInput() {
        Scanner in = new Scanner(System.in);
        int rectanglesCount = Integer.parseInt(in.nextLine());
        int[][] plane = new int[2000][2000];

        for (int i = 0; i < rectanglesCount; i++) {
            String[] rectangleData = in.nextLine().split(" ");
            int x1 = Integer.parseInt(rectangleData[0]) + 1000;
            int x2 = Integer.parseInt(rectangleData[1]) + 1000;
            int y1 = Integer.parseInt(rectangleData[2]) + 1000;
            int y2 = Integer.parseInt(rectangleData[3]) + 1000;

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    plane[j][k]++;
                }
            }
        }
        return plane;
    }

    private static int calcTotalArea(int[][] area) {
        int totalIntersectionArea = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                if (area[i][j] > 1) {
                    totalIntersectionArea++;
                }
            }
        }
        return totalIntersectionArea;
    }
}