package PartV_Exercise._2_Balls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Balls {
    private static int[] result;
    private static int pockets;
    private static int pocketCapacity;
    private static final StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        pockets = Integer.parseInt(reader.readLine());
        int balls = Integer.parseInt(reader.readLine());
        pocketCapacity = Integer.parseInt(reader.readLine());
        result = new int[pockets];

        generate(0, balls);

        System.out.print(output);
    }

    private static void generate(int index, int ballsLeft) {
        if (index == pockets) {
            if (ballsLeft == 0) {
                output.append(Arrays.stream(result)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(", ")))
                        .append(System.lineSeparator());
            }

            return;
        }

        int ballsToPut = ballsLeft - (pockets - (index + 1));
        if (ballsToPut > pocketCapacity) {
            ballsToPut = pocketCapacity;
        }

        for (int i = ballsToPut; i > 0; i--) {
            result[index] = i;
            generate(index + 1, ballsLeft - i);
        }
    }
}
