package PartI_Exercise._2_NonCrossingBridges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NonCrossingBridges {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] sequence = in.readLine().split(" ");
        boolean[] isConnected = new boolean[sequence.length];
        int bridgesFound = 0;
        int lastBridgeIndex = 0;

        for (int i = 1; i < sequence.length; i++) {
            for (int j = lastBridgeIndex; j < i; j++) {
                if (sequence[i].equals(sequence[j])) {
                    bridgesFound++;
                    isConnected[i] = true;
                    isConnected[j] = true;
                    lastBridgeIndex = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (bridgesFound == 1) {
            sb.append(String.format("%d bridge found%n", bridgesFound));
        } else if (bridgesFound == 0) {
            sb.append(String.format("No bridges found%n"));
        } else {
            sb.append(String.format("%d bridges found%n", bridgesFound));
        }

        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[i]) {
                sb.append(sequence[i]).append(" ");
            } else {
                sb.append("X ");
            }
        }
        System.out.print(sb.toString().trim());
    }
}