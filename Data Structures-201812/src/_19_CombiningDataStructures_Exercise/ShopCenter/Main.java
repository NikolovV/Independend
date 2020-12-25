package _19_CombiningDataStructures_Exercise.ShopCenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commandCount = Integer.parseInt(reader.readLine());
        ShoppingCenter shoppingCenter = new ShoppingCenter();

        for (int i = 0; i < commandCount; i++) {
            String input = reader.readLine();
            String command = input.split(" ")[0];
            String[] tokens;
            List<Product> result;
            switch (command) {
                case "AddProduct":
                    tokens = input.substring(11).split(";");
                    shoppingCenter.setLogHistory((shoppingCenter.addProduct(tokens[0], Double.parseDouble(tokens[1]), tokens[2])));
                    break;
                case "DeleteProducts":
                    tokens = input.substring(15).split(";");
                    if (tokens.length == 1) {
                        shoppingCenter.setLogHistory(shoppingCenter.deleteProducts(tokens[0]));
                    } else {
                        shoppingCenter.setLogHistory(shoppingCenter.deleteProducts(tokens[0], tokens[1]));
                    }
                    break;
                case "FindProductsByName":
                    result = shoppingCenter.findProductsByName(input.substring(19));
                    shoppingCenter.appendListResult(result);
                    break;
                case "FindProductsByProducer":
                    result = shoppingCenter.findProductsByProducer(input.substring(23));
                    shoppingCenter.appendListResult(result);
                    break;
                case "FindProductsByPriceRange":
                    tokens = input.substring(25).split(";");
                    double from = Double.parseDouble(tokens[0]);
                    double to = Double.parseDouble(tokens[1]);
                    result = shoppingCenter.findProductsByPriceRange(from, to);
                    shoppingCenter.appendListResult(result);
                    break;
            }
        }
        System.out.println(shoppingCenter.getLogHistory());
    }

}
