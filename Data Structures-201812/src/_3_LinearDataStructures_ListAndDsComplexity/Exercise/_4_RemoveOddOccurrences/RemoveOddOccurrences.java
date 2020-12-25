package _3_LinearDataStructures_ListAndDsComplexity.Exercise._4_RemoveOddOccurrences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RemoveOddOccurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        Map<Integer, Integer> oddTimes = new TreeMap<>();
        for (Integer num : numbers) {
            if (!oddTimes.containsKey(num)) {
                oddTimes.put(num, 1);
            } else {
                oddTimes.put(num, oddTimes.get(num) + 1);
            }
        }

        numbers.removeAll(oddTimes.keySet().stream().filter(x -> oddTimes.get(x) % 2 == 1).collect(Collectors.toList()));
        System.out.println(numbers.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
