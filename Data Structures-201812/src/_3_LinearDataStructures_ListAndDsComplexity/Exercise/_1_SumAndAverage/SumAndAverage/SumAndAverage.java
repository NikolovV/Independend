package _3_LinearDataStructures_ListAndDsComplexity.Exercise._1_SumAndAverage.SumAndAverage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SumAndAverage {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line = in.readLine().split(" ");
        List<Integer> elements = new ArrayList<>();
        for (String s : line) {
            elements.add(Integer.parseInt(s));
        }

        int sum = elements.stream().mapToInt(Integer::intValue).sum();
        float average = (float) sum / elements.size();

        System.out.printf("Sum=%d; Average=%.2f", sum, average);
    }
}
