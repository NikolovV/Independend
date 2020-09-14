package _4_RodCutting;

import java.util.Arrays;
import java.util.Scanner;

public class RodCutting {
    private static int[] prices;
    private static int[] bestPrice;
    private static int[] bestCombo;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        prices = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bestPrice = new int[prices.length];
        Arrays.fill(bestPrice, -1);
        bestCombo = new int[prices.length];

        int rodLength = in.nextInt();

//        System.out.println(cutRodIter(rodLength));
        System.out.println(cutRod(rodLength));

        reconstructSolution(rodLength);
    }

    private static int cutRod(int rod) {
        if (bestPrice[rod] >= 0) {
            return bestPrice[rod];
        }
        if (rod == 0) {
            return 0;
        }
        int currentBest = bestPrice[rod];
        for (int rodPieceLength = 1; rodPieceLength <= rod; rodPieceLength++) {
            currentBest = Math.max(currentBest, prices[rodPieceLength] + cutRod(rod - rodPieceLength));
            if (currentBest > bestPrice[rod]) {
                bestPrice[rod] = currentBest;
                bestCombo[rod] = rodPieceLength;
            }
        }
        return bestPrice[rod];
    }

    private static int cutRodIter(int n) {
        for (int i = 1; i <= n; i++) {
            int currentBest = bestPrice[i];
            for (int j = 1; j <= i; j++) {
                currentBest = Math.max(bestPrice[i], prices[j] + bestPrice[i - j]);
                if (currentBest > bestPrice[i]) {
                    bestPrice[i] = currentBest;
                    bestCombo[i] = j;
                }
            }
        }
        return bestPrice[n];
    }

    private static void reconstructSolution(int rod) {
        while (rod - bestCombo[rod] != 0) {
            System.out.print(bestCombo[rod] + " ");
            rod = rod - bestCombo[rod];
        }

        System.out.println(bestCombo[rod]);
    }

}
