package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.List;

public class CommandParser {
    public static ICommandInvoker parseCommand(String text, ShoppingCenter shoppingCenter) {
        String command = text.split(" ")[0];
        String[] tokens;
        ICommand<String> commandStr;
        ICommand<List<Product>> commandLst;
        ICommandInvoker invoker = null;

        switch (command) {
            case "AddProduct":
                tokens = text.substring(11).split(";");
                commandStr = new AddCommand(new Product(tokens[0], Double.parseDouble(tokens[1]), tokens[2]));
                invoker = new CommandInvokerStr(commandStr, shoppingCenter);
                break;

            case "DeleteProducts":
                tokens = text.substring(15).split(";");
                if (tokens.length == 1) {
                    commandStr = new DeleteProductsProducerCommand(tokens[0]);
                } else {
                    commandStr = new DeleteProductsProducerAndNameCommand(tokens[0], tokens[1]);
                }
                invoker = new CommandInvokerStr(commandStr, shoppingCenter);
                break;

            case "FindProductsByName":
                commandLst = new FindProductsByName(text.substring(19));
                invoker = new CommandInvokerLst(commandLst, shoppingCenter);
                break;

            case "FindProductsByProducer":
                commandLst = new FindProductsByProducer(text.substring(23));
                invoker = new CommandInvokerLst(commandLst, shoppingCenter);
                break;

            case "FindProductsByPriceRange":
                tokens = text.substring(25).split(";");
                double from = Double.parseDouble(tokens[0]);
                double to = Double.parseDouble(tokens[1]);
                commandLst = new FindProductsByPriceRange(from, to);
                invoker = new CommandInvokerLst(commandLst, shoppingCenter);
                break;
        }
        return invoker;
    }
}
