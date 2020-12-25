package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.ArrayList;

public class AddCommand implements ICommand<String> {
    private final Product product;

    public AddCommand(Product product) {
        this.product = product;
    }

    @Override
    public String execute(ShoppingCenter shoppingCenter) {
        shoppingCenter.getProductsByName().putIfAbsent(this.product.getName(), new ArrayList<>());
        shoppingCenter.getProductsByName().get(this.product.getName()).add(this.product);

        shoppingCenter.getProductsByProducer().putIfAbsent(this.product.getProducer(), new ArrayList<>());
        shoppingCenter.getProductsByProducer().get(this.product.getProducer()).add(this.product);

        String key = this.product.getName().concat(this.product.getProducer());
        shoppingCenter.getProductsByNameAndProducer().putIfAbsent(key, new ArrayList<>());
        shoppingCenter.getProductsByNameAndProducer().get(key).add(this.product);

        shoppingCenter.getProductsByPrice().putIfAbsent(this.product.getPrice(), new ArrayList<>());
        shoppingCenter.getProductsByPrice().get(this.product.getPrice()).add(this.product);

        return ShoppingCenter.PRODUCT_ADDED;
    }
}
