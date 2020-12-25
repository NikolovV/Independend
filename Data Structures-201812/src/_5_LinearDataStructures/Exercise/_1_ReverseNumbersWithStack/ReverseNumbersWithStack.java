package _5_LinearDataStructures.Exercise._1_ReverseNumbersWithStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ReverseNumbersWithStack {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> stack = new Stack<>();
        String[] input = in.readLine().split(" ");
        for (String str : input) {
            stack.push(Integer.parseInt(str));
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
