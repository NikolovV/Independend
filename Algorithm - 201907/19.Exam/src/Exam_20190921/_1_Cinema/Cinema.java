package Exam_20190921._1_Cinema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    private static List<String> names;
    private static String[] positions;
    private static int n;
    private static boolean[] used;
    private static String[] combinations;
    private static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        names = Arrays.stream(reader.readLine().split(", ")).collect(Collectors.toList());
        positions = new String[names.size()];
        n = names.size();
        String line = reader.readLine();
        while (!line.equals("generate")) {

            String[] tokens = line.split(" - ");

            String name = tokens[0];
            int index = Integer.parseInt(tokens[1]);

            positions[index - 1] = name;

            names.remove(name);

            line = reader.readLine();
        }

        combinations = new String[names.size()];
        used = new boolean[names.size()];

        combine(0);

        System.out.println(stringBuilder.toString().trim());
    }

    private static void combine(int index) {
        if (index >= names.size()) {
            addCombo();
        } else {
            for (int i = 0; i < names.size(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    combinations[index] = names.get(i);
                    combine(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void addCombo() {
        int counter = 0;
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (positions[i] != null) {
                line.append(positions[i]).append(" ");
            } else {
                line.append(combinations[counter++]).append(" ");
            }
        }

        stringBuilder.append(line.toString().trim()).append(System.lineSeparator());
    }
}
