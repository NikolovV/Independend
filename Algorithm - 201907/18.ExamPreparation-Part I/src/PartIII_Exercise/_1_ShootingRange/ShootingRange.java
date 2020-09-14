package PartIII_Exercise._1_ShootingRange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShootingRange {
    private static int[] targets;
    private static int targetSum;
    private static boolean[] marked;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        targets = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        targetSum = Integer.parseInt(in.readLine());
        marked = new boolean[targets.length];

        solve(0);
    }

    private static void solve(int index) {
        int currentSum = getSum();

        if (currentSum == targetSum) {
            printResult();
        }

        if (index >= targets.length || currentSum >= targetSum) {
            return;
        } else {
            Set<Integer> usedElements = new HashSet<>();

            for (int i = index; i < targets.length; i++) {
                if (!usedElements.contains(targets[i])) {
                    swap(index, i);
                    marked[index] = true;
                    solve(index + 1);
                    swap(index, i);
                    marked[index] = false;
                    usedElements.add(targets[i]);
                }
            }
        }
    }

    private static int getSum() {
        int currentSum = 0;
        int multiplier = 1;

        for (int i = 0; i < targets.length; i++) {
            if (marked[i]) {
                currentSum += targets[i] * multiplier;
                multiplier++;
            }
        }

        return currentSum;
    }

    private static void swap(int i, int j) {
        int temp = targets[i];
        targets[i] = targets[j];
        targets[j] = temp;
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < targets.length; i++) {
            if (marked[i]) {
                sb.append(targets[i]).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }
}
