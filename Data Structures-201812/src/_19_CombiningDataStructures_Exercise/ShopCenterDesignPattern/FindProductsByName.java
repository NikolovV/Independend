package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.List;

public class FindProductsByName implements ICommand<List<Product>> {
    private final String productName;

    public FindProductsByName(String productName) {
        this.productName = productName;
    }

    @Override
    public List<Product> execute(ShoppingCenter shoppingCenter) {
        return shoppingCenter.getProductsByName().get(this.productName);
    }
}