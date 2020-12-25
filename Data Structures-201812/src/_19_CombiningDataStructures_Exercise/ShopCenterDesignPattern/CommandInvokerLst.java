package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.List;

public class CommandInvokerLst implements ICommandInvoker {
    private final ICommand<List<Product>> commandLst;
    private final ShoppingCenter shoppingCenter;

    public CommandInvokerLst(ICommand<List<Product>> commandLst, ShoppingCenter shoppingCenter) {
        this.commandLst = commandLst;
        this.shoppingCenter = shoppingCenter;
    }

    @Override
    public String executeCommand() {
        return ShoppingCenter.appendListResult((this.commandLst.execute(this.shoppingCenter)));
    }
}
