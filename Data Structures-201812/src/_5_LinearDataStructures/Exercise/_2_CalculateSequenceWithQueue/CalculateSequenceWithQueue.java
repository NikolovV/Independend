package _5_LinearDataStructures.Exercise._2_CalculateSequenceWithQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class CalculateSequenceWithQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        int count = 0;

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty() && count < 50) {
            int num = queue.poll();
            sb.append(num).append(", ");
            if (queue.size() + count < 50) {
                queue.offer(num + 1);
                queue.offer(2 * num + 1);
                queue.offer(num + 2);
            }
            count++;
        }

        sb.replace(sb.length() - 2, sb.length(), "");
        System.out.println(sb.toString());
    }
}
