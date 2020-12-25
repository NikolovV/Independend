package _19_CombiningDataStructures_Exercise.ShopCenter;

import java.util.*;

public class ShoppingCenter {
    private final Map<String, List<Product>> productsByName;
    private final Map<String, List<Product>> productsByProducer;
    private final Map<String, List<Product>> productsByNameAndProducer;
    private final TreeMap<Double, List<Product>> productsByPrice;
    private final StringBuilder logHistory;
    private final String NO_PRODUCT_FOUND = "No products found";
    private final String PRODUCT_ADDED = "Product added";

    public ShoppingCenter() {
        this.productsByName = new HashMap<>();
        this.productsByProducer = new HashMap<>();
        this.productsByNameAndProducer = new HashMap<>();
        this.productsByPrice = new TreeMap<>();
        this.logHistory = new StringBuilder();
    }

    public String addProduct(String name, double price, String producer) {
        Product product = new Product(name, price, producer);

        this.productsByName.putIfAbsent(name, new ArrayList<>());
        this.productsByName.get(name).add(product);

        this.productsByProducer.putIfAbsent(producer, new ArrayList<>());
        this.productsByProducer.get(producer).add(product);

        String key = name.concat(producer);
        this.productsByNameAndProducer.putIfAbsent(key, new ArrayList<>());
        this.productsByNameAndProducer.get(key).add(product);

        this.productsByPrice.putIfAbsent(price, new ArrayList<>());
        this.productsByPrice.get(price).add(product);

        return this.PRODUCT_ADDED;
    }

    public String deleteProducts(String producer) {
        String result;
        if (this.productsByProducer.containsKey(producer) &&
                this.productsByProducer.get(producer).size() > 0) {
            int productsCount = this.productsByProducer.get(producer).size();
            result = String.format("%d products deleted", productsCount);

            List<Product> productsToRemove = this.productsByProducer.get(producer);
            for (Product product : productsToRemove) {
                this.productsByName.get(product.getName()).remove(product);
                this.productsByNameAndProducer.get(product.getName() + product.getProducer()).remove(product);
                this.productsByPrice.get(product.getPrice()).remove(product);
            }
            this.productsByProducer.remove(producer);
        } else {
            return this.NO_PRODUCT_FOUND;
        }
        return result;
    }

    public String deleteProducts(String name, String producer) {
        String key = name.concat(producer);
        String result;
        if (this.productsByNameAndProducer.containsKey(key) &&
                this.productsByNameAndProducer.get(key).size() > 0) {
            int productsCount = this.productsByNameAndProducer.get(key).size();
            result = String.format("%d products deleted", productsCount);

            List<Product> productsToRemove = this.productsByNameAndProducer.get(key);
            for (Product product : productsToRemove) {
                this.productsByName.get(product.getName()).remove(product);
                this.productsByProducer.get(product.getProducer()).remove(product);
                this.productsByPrice.get(product.getPrice()).remove(product);
            }
            this.productsByNameAndProducer.remove(key);
        } else {
            return this.NO_PRODUCT_FOUND;
        }
        return result;
    }

    public List<Product> findProductsByName(String name) {
        return this.productsByName.get(name);
    }

    public List<Product> findProductsByProducer(String producer) {
        return this.productsByProducer.get(producer);
    }

    public List<Product> findProductsByPriceRange(double from, double to) {
        List<Product> result = new ArrayList<>();
        this.productsByPrice.subMap(from, true, to, true)
                .forEach((key, value) -> result.addAll(value));
        return result;
    }

    public void appendListResult(List<Product> result) {
        if (result == null || result.isEmpty()) {
            this.logHistory.append(this.NO_PRODUCT_FOUND).append(System.lineSeparator());
        } else {
            result.sort(Comparator.naturalOrder());
            for (Product product : result) {
                this.logHistory.append(product).append(System.lineSeparator());
            }
        }
    }

    public StringBuilder getLogHistory() {
        return this.logHistory;
    }

    public void setLogHistory(String message) {
        this.logHistory.append(message).append(System.lineSeparator());
    }
}
