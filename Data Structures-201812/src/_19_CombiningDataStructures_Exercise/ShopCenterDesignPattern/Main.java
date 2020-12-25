package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commandCount = Integer.parseInt(reader.readLine());

        ShoppingCenter shoppingCenter = new ShoppingCenter();
        ICommandInvoker invoker;
        for (int i = 0; i < commandCount; i++) {
            String input = reader.readLine();
            invoker = CommandParser.parseCommand(input, shoppingCenter);
            shoppingCenter.setLogHistory(invoker.executeCommand());
        }

        System.out.print(shoppingCenter.getLogHistory());
    }
}
