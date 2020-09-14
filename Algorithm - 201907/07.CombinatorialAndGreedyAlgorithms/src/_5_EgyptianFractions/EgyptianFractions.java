package _5_EgyptianFractions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EgyptianFractions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] fraction = in.nextLine().split("/");
        long numerator = Long.parseLong(fraction[0]);
        long denominator = Long.parseLong(fraction[1]);

        if (numerator > denominator) {
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }
        System.out.printf("%d/%d = ", numerator, denominator);

        List<Integer> denominatorsList = new ArrayList<>();

        int denominatorIndex = 2;

        while (numerator != 0) {
            long nextNumerator = numerator * denominatorIndex;
            long nextDenominator = denominator;

            long remaining = nextNumerator - nextDenominator;
            if (remaining < 0) {
                denominatorIndex++;
                continue;
            }
            denominatorsList.add(denominatorIndex);
            numerator = remaining;
            denominator = denominator * denominatorIndex;
            denominatorIndex++;
        }

        System.out.println(denominatorsList.stream().map(Objects::toString).map(s -> "1/" + s).collect(Collectors.joining(" + ")));
    }
}
