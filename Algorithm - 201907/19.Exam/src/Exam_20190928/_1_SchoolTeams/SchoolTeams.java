package Exam_20190928._1_SchoolTeams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SchoolTeams {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] girls = reader.readLine().split(", ");
        String[] boys = reader.readLine().split(", ");

        List<String> girlsBuilder = new ArrayList<>();
        List<String> boysBuilder = new ArrayList<>();
        String[] girlsCombinations = new String[3];
        String[] boysCombinations = new String[2];
        generateCombo(girls, 0, 0, girlsCombinations, girlsBuilder);
        generateCombo(boys, 0, 0, boysCombinations, boysBuilder);

        for (String s : girlsBuilder) {
            for (String value : boysBuilder) {
                System.out.println(s + ", " + value);
            }
        }
    }

    private static void generateCombo(String[] data, int index, int start, String[] combinations, List<String> builderList) {
        if (index >= combinations.length) {
            builderList.add(String.join(", ", combinations));
        } else {
            for (int i = start; i < data.length; i++) {
                combinations[index] = data[i];
                generateCombo(data, index + 1, i + 1, combinations, builderList);
            }
        }
    }
}
