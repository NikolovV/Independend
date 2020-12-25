package _3_LinearDataStructures_ListAndDsComplexity.Exercise._5_CountOfOccurrences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CountOfOccurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        Map<Integer, Integer> occurrence = new TreeMap<>();
        for (Integer num : numbers) {
            if (!occurrence.containsKey(num)) {
                occurrence.put(num, 1);
            } else {
                occurrence.put(num, occurrence.get(num) + 1);
            }
        }

        occurrence.forEach((key, value) -> System.out.println(key + " -> " + value + " times"));
    }
}
