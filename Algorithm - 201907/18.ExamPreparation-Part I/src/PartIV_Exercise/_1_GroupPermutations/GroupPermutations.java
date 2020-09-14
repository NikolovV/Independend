package PartIV_Exercise._1_GroupPermutations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GroupPermutations {
    private static String[] distinctElements;
    private static final Map<String, Integer> occurrences = new HashMap<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        String[] elementsData = input.split("");
        distinctElements = getDistinctElements(input.toCharArray(), input.length()).split("");

        for (String element : elementsData) {
            occurrences.putIfAbsent(element, 0);
            occurrences.put(element, occurrences.get(element) + 1);
        }

        permute(0);
        System.out.print(sb);
    }

    private static String getDistinctElements(char[] str, int n) {
        Set<String> set = new LinkedHashSet<>();
        for (char c : str) {
            set.add(String.valueOf(c));
        }

        return String.join("", set);
    }

    private static void permute(int index) {
        if (index >= distinctElements.length) {
            for (String element : distinctElements) {
                sb.append(new String(new char[occurrences.get(element)]).replace("\0", element));
            }
            sb.append(System.lineSeparator());
        } else {
            permute(index + 1);

            for (int i = index + 1; i < distinctElements.length; i++) {
                swap(index, i);
                permute(index + 1);
                swap(index, i);
            }
        }
    }

    private static void swap(int i, int j) {
        String tmp = distinctElements[i];
        distinctElements[i] = distinctElements[j];
        distinctElements[j] = tmp;
    }

}
