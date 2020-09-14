package _1_ConnectingCables;

import java.util.Arrays;
import java.util.Scanner;

public class ConnectingCables {
    private static int[] pinsTop;
    private static int[] pinsBottom;
    private static int[][] maxSol;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        pinsBottom = /*new int[]{2, 5, 3, 8, 7, 4, 6, 9, 1};*/Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        pinsTop = Arrays.stream(pinsBottom).sorted().toArray();

        maxSol = new int[pinsTop.length + 1][pinsTop.length + 1];
        for (int i = 1; i < maxSol.length; i++) {
            for (int j = 1; j < maxSol[0].length; j++) {
                maxSol[i][j] = -1;
            }
        }

        System.out.println("Maximum pairs connected: " + connectedPairs(pinsTop.length, pinsBottom.length));
        System.out.println("Maximum pairs connected: " + connectedPairsIter());
    }

    private static int connectedPairs(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }
        if (maxSol[x][y] != -1) {
            return maxSol[x][y];
        }
        if (pinsTop[x - 1] == pinsBottom[y - 1]) {
            maxSol[x][y] = 1 + connectedPairs(x - 1, y - 1);
        } else {
            int top = connectedPairs(x - 1, y);
            int left = connectedPairs(x, y - 1);

            maxSol[x][y] = Integer.max(top, left);
        }

        return maxSol[x][y];
    }

    private static int connectedPairsIter() {

        int[] maxPiars = new int[pinsBottom.length];
        Arrays.fill(maxPiars, 1);
        int maxConnecetd = 1;

        for (int i = pinsBottom.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < pinsBottom.length; j++) {
                if (pinsBottom[i] < pinsBottom[j] && maxPiars[i] < 1 + maxPiars[j]) {
                    maxPiars[i] = 1 + maxPiars[j];
                }
                if (maxConnecetd < maxPiars[i]) {
                    maxConnecetd = maxPiars[i];
                }
            }
        }
        return maxConnecetd;
    }
}
