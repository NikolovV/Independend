package PartI._2_CableMerchant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CableMerchant {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = in.readLine().split(" ");
        int[] pricesPerUnit = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            pricesPerUnit[i] = Integer.parseInt(tokens[i]);
        }

        int connectorPrice = Integer.parseInt(in.readLine());
        for (int wholePiece = 1; wholePiece < pricesPerUnit.length; wholePiece++) {
            for (int splitPiece = 0; splitPiece < wholePiece / 2; splitPiece++) {
                int price = pricesPerUnit[splitPiece] + pricesPerUnit[wholePiece - 1 - splitPiece] - 2 * connectorPrice;
                if (price > pricesPerUnit[wholePiece]) {
                    pricesPerUnit[wholePiece] = price;
                }
            }
            if (wholePiece % 2 == 1) {
                int price = (pricesPerUnit[wholePiece / 2] - connectorPrice) * 2;
                if (price > pricesPerUnit[wholePiece]) {
                    pricesPerUnit[wholePiece] = price;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int price : pricesPerUnit) {
            sb.append(price).append(" ");
        }
        System.out.println(sb);

    }
}
