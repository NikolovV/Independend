package _3_LinearDataStructures_ListAndDsComplexity.Exercise._2_SortWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortWords {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line = in.readLine().split(" ");
        List<String> stringList = Arrays.stream(line).sequential().sorted().collect(Collectors.toList());

        stringList.forEach(x -> System.out.print(x + " "));
    }
}
