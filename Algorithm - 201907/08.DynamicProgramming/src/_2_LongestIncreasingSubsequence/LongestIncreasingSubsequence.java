package _2_LongestIncreasingSubsequence;

import java.util.*;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence {
    private static int[] numbers;
    private static int[] lengthLIS;
    private static int[] prevIndex;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        numbers = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        lengthLIS = new int[numbers.length];
        prevIndex = new int[numbers.length];

        int[] lis = longestIncreasingSubsequence();
        System.out.println(Arrays.stream(lis).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static int[] longestIncreasingSubsequence() {
        int maxLength = 0;
        int lastIndex = -1;
        for (int seqIndex = 0; seqIndex < numbers.length; seqIndex++) {
            lengthLIS[seqIndex] = 1;
            prevIndex[seqIndex] = -1;
            for (int i = 0; i < seqIndex; i++) {
                if (numbers[i] < numbers[seqIndex] && lengthLIS[i] + 1 > lengthLIS[seqIndex]) {
                    lengthLIS[seqIndex] = 1 + lengthLIS[i];
                    prevIndex[seqIndex] = i;
                }
            }
            if (lengthLIS[seqIndex] > maxLength) {
                maxLength = lengthLIS[seqIndex];
                lastIndex = seqIndex;
            }
        }
        return restoreLIS(lastIndex);
    }

    private static int[] restoreLIS(int lastIndex) {
        List<Integer> longestSeq = new ArrayList<>();
        while (lastIndex != -1) {
            longestSeq.add(numbers[lastIndex]);
            lastIndex = prevIndex[lastIndex];
        }
        longestSeq.sort(Collections.reverseOrder().reversed());
        return Arrays.stream(longestSeq.toArray()).mapToInt(Object::hashCode).toArray();
    }

}
