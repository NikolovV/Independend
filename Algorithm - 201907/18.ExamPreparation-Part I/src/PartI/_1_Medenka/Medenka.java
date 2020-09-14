package PartI._1_Medenka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Medenka {
    private static final StringBuilder sbOutput = new StringBuilder();
    private static final Deque<Integer> indices = new ArrayDeque<>();
    private static String medenka;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        medenka = in.readLine().replaceAll(" ", "");
        int startIndex = medenka.indexOf("1");
        generate(startIndex);
        System.out.print(sbOutput);
    }

    private static void generate(int startIndex) {
        int nextIndex = medenka.indexOf("1", startIndex + 1);
        if (nextIndex == -1) {
            addMedenka();
            return;
        }
        for (int i = startIndex + 1; i <= nextIndex; i++) {
            indices.push(i);
            generate(nextIndex);
            indices.pop();
        }
    }

    private static void addMedenka() {
        StringBuilder sb = new StringBuilder(medenka);
        for (Integer index : indices) {
            sb.insert(index, "|");
        }
        sbOutput.append(sb).append(System.lineSeparator());
    }
}
