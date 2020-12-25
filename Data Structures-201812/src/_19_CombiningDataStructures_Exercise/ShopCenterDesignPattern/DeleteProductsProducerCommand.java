package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.List;

public class DeleteProductsProducerCommand implements ICommand<String> {
    private final String producer;

    public DeleteProductsProducerCommand(String producer) {
        this.producer = producer;
    }

    @Override
    public String execute(ShoppingCenter shoppingCenter) {
        String result;
        if (shoppingCenter.getProductsByProducer().containsKey(this.producer) &&
                shoppingCenter.getProductsByProducer().get(this.producer).size() > 0) {
            int productsCount = shoppingCenter.getProductsByProducer().get(this.producer).size();
            result = String.format("%d products deleted", productsCount);

            List<Product> productsToRemove = shoppingCenter.getProductsByProducer().get(this.producer);
            for (Product product : productsToRemove) {
                shoppingCenter.getProductsByName().get(product.getName()).remove(product);
                shoppingCenter.getProductsByNameAndProducer().get(product.getName() + product.getProducer()).remove(product);
                shoppingCenter.getProductsByPrice().get(product.getPrice()).remove(product);
            }
            shoppingCenter.getProductsByProducer().remove(this.producer);
        } else {
            result = ShoppingCenter.NO_PRODUCT_FOUND;
        }
        return result;
    }
}
