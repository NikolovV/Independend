package PartI_Exercise._1_SequencesOfLimitedSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class SequencesOfLimitedSum {
    private static int totalSum;
    private static final Deque<Integer> sequences = new ArrayDeque<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        totalSum = Integer.parseInt(in.readLine());
        generate(0);
        System.out.print(sb);
    }

    private static void generate(int sum) {
        for (int i = 1; i <= totalSum; i++) {
            if (sum + i > totalSum) {
                return;
            }
            sequences.addLast(i);
            appendSequence();
            generate(sum + i);
            sequences.removeLast();
        }
    }

    private static void appendSequence() {
        for (Integer element : sequences) {
            sb.append(element).append(" ");
        }
        sb.append(System.lineSeparator());
    }
}
