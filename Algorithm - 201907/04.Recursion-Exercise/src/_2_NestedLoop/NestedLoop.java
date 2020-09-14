package _2_NestedLoop;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NestedLoop {
    public static Integer[] numArr;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        numArr = new Integer[n];
        nestedLoop(0);
    }

    public static void nestedLoop(int index) {
        if (index > numArr.length - 1) {
//            System.out.println(Stream.of(numArr).map(String::valueOf).collect(Collectors.joining(" ")));
            System.out.println(Arrays.stream(numArr).map(String::valueOf).collect(Collectors.joining(" ")));
            return;
        }

        for (int row = 1; row <= numArr.length; row++) {
            numArr[index] = row;
            nestedLoop(index + 1);
            if (row == numArr.length) {
                index--;
            }
        }
    }
}

