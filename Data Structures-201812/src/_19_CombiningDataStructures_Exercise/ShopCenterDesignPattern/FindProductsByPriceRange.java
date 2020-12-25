package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class FindProductsByPriceRange implements ICommand<List<Product>> {
    private final double startPrice;
    private final double endPrice;

    public FindProductsByPriceRange(double startPrice, double endPrice) {
        this.startPrice = startPrice;
        this.endPrice = endPrice;
    }

    @Override
    public List<Product> execute(ShoppingCenter shoppingCenter) {
        List<Product> result = new ArrayList<>();
        shoppingCenter.getProductsByPrice().subMap(this.startPrice, true, this.endPrice, true)
                .forEach((key, value) -> result.addAll(value));
        return result;
    }
}
