package _3_RecursiveDraw;

import java.util.Collections;
import java.util.Scanner;

public class RecursiveDraw {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        draw(n);
//        draw(n, n);

        in.close();
    }

    public static void draw(int n) {
        if (n < 1) {
            return;
        }

        System.out.println(String.join("", Collections.nCopies(n, "*")));
        draw(n - 1);
        System.out.println(String.join("", Collections.nCopies(n, "#")));
    }

    // Sandglass
    public static void draw(int n, int size) {
        if (n < 1) {
            return;
        }

        System.out.print(String.join("", Collections.nCopies((size - n), " ")));
		System.out.println(String.join("", Collections.nCopies(n*2,"*")));
        draw(n - 1, size);
		System.out.print(String.join("", Collections.nCopies((size - n), " ")));
        System.out.println(String.join("", Collections.nCopies(n*2, "#")));
    }
}
