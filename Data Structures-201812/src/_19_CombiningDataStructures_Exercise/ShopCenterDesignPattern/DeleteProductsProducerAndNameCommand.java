package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.List;

public class DeleteProductsProducerAndNameCommand implements ICommand<String> {
    private final String producer;
    private final String productName;

    public DeleteProductsProducerAndNameCommand(String productName, String producer) {
        this.producer = producer;
        this.productName = productName;
    }

    @Override
    public String execute(ShoppingCenter shoppingCenter) {
        String key = this.productName.concat(this.producer);
        String result;
        if (shoppingCenter.getProductsByNameAndProducer().containsKey(key) &&
                shoppingCenter.getProductsByNameAndProducer().get(key).size() > 0) {
            int productsCount = shoppingCenter.getProductsByNameAndProducer().get(key).size();
            result = String.format("%d products deleted", productsCount);

            List<Product> productsToRemove = shoppingCenter.getProductsByNameAndProducer().get(key);
            for (Product product : productsToRemove) {
                shoppingCenter.getProductsByName().get(product.getName()).remove(product);
                shoppingCenter.getProductsByProducer().get(product.getProducer()).remove(product);
                shoppingCenter.getProductsByPrice().get(product.getPrice()).remove(product);
            }
            shoppingCenter.getProductsByNameAndProducer().remove(key);
        } else {
            result = ShoppingCenter.NO_PRODUCT_FOUND;
        }
        return result;
    }
}
