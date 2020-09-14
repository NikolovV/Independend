package _5_Needles;

import java.util.Scanner;

public class Needles {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        in.nextLine();
        String[] sizes = in.nextLine().split(" ");
        int sequenceSize = Integer.parseInt(sizes[0]);
        int needleSize = Integer.parseInt(sizes[1]);
        //                0  1   2  3  4  5   6   7  8  9 10  11  12  13  14  15 16   17   18   19   20   21  22
        String[] strSequence = in.nextLine().split(" ");
        int[] sequence = new int[sequenceSize];
        for (int i = 0; i < sequenceSize; i++) {
            sequence[i] = Integer.parseInt(strSequence[i]);
        }
//
        String[] strNeedles = in.nextLine().split(" ");
        int[] needles = new int[needleSize];
        for (int i = 0; i < needleSize; i++) {
            needles[i] = Integer.parseInt(strNeedles[i]);
        }

//        int[] sequence = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();//{3, 5, 11, 0, 0, 0, 12, 12, 0, 0, 0, 12, 12, 70, 71, 0, 90, 123, 140, 150, 166, 190, 0}; //
//        int[] needles = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(); //{5, 13, 90, 1, 70, 75, 7, 188, 12}; //

        putNeelde(sequence, needles);
    }

    private static void putNeelde(int[] sequence, int[] needles) {
        for (int i = 0; i < needles.length; i++) {
            System.out.print(String.format("%d ", getPlace(sequence, needles[i])));
        }
    }

    private static int getPlace(int[] sequence, int needle) {
        int j = 0;
        if (needle > sequence[sequence.length - 1] && sequence[sequence.length - 1] != 0) {
            return sequence.length;
        }
        while (sequence[j] < needle && j < sequence.length - 1) {
            j++;
        }
        while (j > 0 && sequence[j - 1] == 0) {
            j--;
        }
        return j;
    }
}
