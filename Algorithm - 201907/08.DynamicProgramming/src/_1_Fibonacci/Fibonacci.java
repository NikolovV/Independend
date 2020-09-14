package _1_Fibonacci;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Fibonacci {
    private static Map<Integer, Integer> cashedFib = new TreeMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        System.out.println(fibonacci(number));
    }

    private static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else if (n == 0) {
            return 0;
        } else {
            if (!cashedFib.containsKey(n)) {
                cashedFib.put(n, fibonacci(n - 1) + fibonacci(n - 2));
            }
            return cashedFib.get(n);
        }
    }
}
