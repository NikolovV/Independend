package PartII_Exercise._4_Island;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Island {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] columnsArea = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int maxArea = 0;

        for (int i = 0; i < columnsArea.length; i++) {
            int currentColumn = columnsArea[i];
            int counter = 1;
            boolean lookForward = true;

            for (int j = i - 1; j >= 0; j--) {
                if (columnsArea[j] == currentColumn) {
                    counter = 0;
                    lookForward = false;
                    break;
                } else if (columnsArea[j] > currentColumn) {
                    counter++;
                } else {
                    break;
                }
            }

            if (lookForward) {
                for (int j = i + 1; j < columnsArea.length; j++) {
                    if (columnsArea[j] >= currentColumn) {
                        counter++;
                    } else {
                        break;
                    }
                }
            }

            int area = counter * currentColumn;
            if (area > maxArea) {
                maxArea = area;
            }
        }
        
        System.out.print(maxArea);
    }
}
