package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.List;

public class FindProductsByProducer implements ICommand<List<Product>> {
    private final String producer;

    public FindProductsByProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public List<Product> execute(ShoppingCenter shoppingCenter) {
        return shoppingCenter.getProductsByProducer().get(this.producer);
    }
}
