package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

import java.util.*;

public class ShoppingCenter {
    private final Map<String, List<Product>> productsByName;
    private final Map<String, List<Product>> productsByProducer;
    private final Map<String, List<Product>> productsByNameAndProducer;
    private final TreeMap<Double, List<Product>> productsByPrice;
    private final StringBuilder logHistory;
    public static final String NO_PRODUCT_FOUND = "No products found";
    public static final String PRODUCT_ADDED = "Product added";

    public ShoppingCenter() {
        this.productsByName = new HashMap<>();
        this.productsByProducer = new HashMap<>();
        this.productsByNameAndProducer = new HashMap<>();
        this.productsByPrice = new TreeMap<>();
        this.logHistory = new StringBuilder();
    }

    public Map<String, List<Product>> getProductsByName() {
        return this.productsByName;
    }

    public Map<String, List<Product>> getProductsByProducer() {
        return this.productsByProducer;
    }

    public Map<String, List<Product>> getProductsByNameAndProducer() {
        return this.productsByNameAndProducer;
    }

    public TreeMap<Double, List<Product>> getProductsByPrice() {
        return this.productsByPrice;
    }

    public static String appendListResult(List<Product> result) {
        StringBuilder resultStr = new StringBuilder();
        if (result == null || result.iterator().hasNext()) {
            resultStr.append(ShoppingCenter.NO_PRODUCT_FOUND);
        } else {
            Collections.sort(result);
            for (Product product : result) {
                resultStr.append(product).append(System.lineSeparator());
            }
        }
        return resultStr.toString().trim();
    }

    public StringBuilder getLogHistory() {
        return this.logHistory;
    }

    public void setLogHistory(String message) {
        this.logHistory.append(message).append(System.lineSeparator());
    }
}
