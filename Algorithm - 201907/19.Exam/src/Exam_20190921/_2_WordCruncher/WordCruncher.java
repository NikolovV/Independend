package Exam_20190921._2_WordCruncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class WordCruncher {
    private static String target;
    private static final List<String> buffer = new ArrayList<>();
    private static final Set<String> result = new HashSet<>();
    private static final StringBuilder sb = new StringBuilder();
    private static final Map<Integer, ArrayList<String>> tree = new HashMap<>();
    private static final Map<String, Integer> resources = new HashMap<>();
    private static final Map<String, Integer> usedCounter = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> pool = Arrays.stream(reader.readLine().split(", ")).collect(Collectors.toList());

        target = reader.readLine();

        ArrayList<String> toRemove = new ArrayList<>();
        for (String str : pool) {
            if (!target.contains(str)) {
                toRemove.add(str);
            } else if (!resources.containsKey(str)) {
                resources.put(str, 1);
                usedCounter.put(str, 0);
            } else {
                resources.put(str, resources.get(str) + 1);
            }
        }
        pool.removeAll(toRemove);

        for (int i = 0; i < target.length(); i++) {
            tree.put(i, new ArrayList<>());
        }

        for (String element : pool) {
            int index = target.indexOf(element);
            while (index != -1) {
                tree.get(index).add(element);
                index = target.indexOf(element, index + 1);
            }
        }

        treeTraverse(0);

        System.out.print(sb.toString());
    }

    private static void treeTraverse(int index) {
        if (index == target.length()) {
            printCombination();
        } else {
            for (int i = 0; i < tree.get(index).size(); i++) {
                String element = tree.get(index).get(i);
                if (resources.get(element) > usedCounter.get(element)) {
                    usedCounter.put(element, usedCounter.get(element) + 1);
                    buffer.add(element);
                    treeTraverse(index + element.length());
                    buffer.remove(buffer.lastIndexOf(element));
                    usedCounter.put(element, usedCounter.get(element) - 1);
                }
            }
        }
    }

    private static void printCombination() {
        String text = String.join("", buffer);
        if (text.equals(target)) {
            String output = String.join(" ", buffer);
            if (result.add(output)) {
                sb.append(output).append(System.lineSeparator());
            }
        }
    }
}
