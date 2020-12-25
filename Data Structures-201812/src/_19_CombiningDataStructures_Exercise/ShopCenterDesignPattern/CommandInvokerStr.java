package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

public class CommandInvokerStr implements ICommandInvoker {
    private final ICommand<String> command;
    private final ShoppingCenter shoppingCenter;

    public CommandInvokerStr(ICommand<String> command, ShoppingCenter shoppingCenter) {
        this.command = command;
        this.shoppingCenter = shoppingCenter;
    }

    @Override
    public String executeCommand() {
        return this.command.execute(this.shoppingCenter);
    }

}
