package _19_CombiningDataStructures_Exercise.ShopCenter;

public class Product implements Comparable<Product> {
    private final String name;
    private final double price;
    private final String producer;

    public Product(String name, double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getProducer() {
        return this.producer;
    }

    @Override
    public int compareTo(Product o) {
        int comp = this.name.compareTo(o.name);
        if (comp == 0) {
            comp = this.producer.compareTo(o.producer);
            if (comp == 0) {
                comp = Double.compare(this.price, o.price);
            }
        }
        return comp;
    }

    @Override
    public String toString() {
        return "{".concat(this.name).concat(";")
                .concat(this.producer).concat(";")
                .concat(String.format("%.2f", this.price)).concat("}");
    }
}
