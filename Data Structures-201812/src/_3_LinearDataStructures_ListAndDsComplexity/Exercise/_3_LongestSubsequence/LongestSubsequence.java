package _3_LinearDataStructures_ListAndDsComplexity.Exercise._3_LongestSubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LongestSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> seq = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int maxCount = 1;
        int value = seq.get(0);
        int currentCount = 1;

        for (int i = 1; i < seq.size(); i++) {
            int currentNumber = seq.get(i);
            int prevNumber = seq.get(i - 1);
            if (currentNumber == prevNumber) {
                currentCount++;
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    value = currentNumber;
                }
            } else {
                currentCount = 1;
            }
        }

        for (int i = 0; i < maxCount; i++) {
            System.out.print(value + " ");
        }
    }
}
