package _2_RecursiveFactorial;

import java.util.Scanner;

public class RecursiveFactorial {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        long factorial = factorial(n);
        System.out.println(factorial);
        in.close();
    }

    public static long factorial(int num) {
        if (num <= 1) {
            return 1;
        }

        return num * factorial(num - 1);
    }
}