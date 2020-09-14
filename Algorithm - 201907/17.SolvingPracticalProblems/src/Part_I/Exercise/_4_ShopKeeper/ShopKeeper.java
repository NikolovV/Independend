package Part_I.Exercise._4_ShopKeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ShopKeeper {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> productsList = Arrays.stream(in.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int[] ordersList = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (!productsList.contains(ordersList[0])) {
            System.out.println("impossible");
            return;
        }

        Map<Integer, Order> orders = new HashMap<>();
        for (int i = ordersList.length - 1; i >= 0; i--) {
            int order = ordersList[i];

            orders.putIfAbsent(order, new Order(order));
            orders.get(order).getOrderPositions().push(i);
        }
        TreeSet<Order> shop = new TreeSet<>();
        Set<Integer> productsListDistinct = new HashSet<>(productsList);

        for (Integer product : productsListDistinct) {
            shop.add(!orders.containsKey(product) ? new Order() : orders.get(product));
        }

        System.out.println(getChanges(ordersList, orders, shop));
    }

    private static int getChanges(int[] ordersList, Map<Integer, Order> orders, TreeSet<Order> shop) {
        int changes = 0;
        for (int order : ordersList) {
            Order currentOrder = orders.get(order);

            if (!shop.contains(currentOrder)) {
                shop.remove(shop.last());
                shop.add(currentOrder);
                changes++;
            }

            shop.remove(currentOrder);
            currentOrder.getOrderPositions().pop();
            shop.add(currentOrder);
        }
        return changes;
    }
}

class Order implements Comparable<Order> {
    private static int tempId = Integer.MAX_VALUE;
    private final int productType;
    private final Deque<Integer> orderPositions;

    Order(int productType) {
        this.productType = productType;
        this.orderPositions = new ArrayDeque<>();
    }

    Order() {
        this.orderPositions = new ArrayDeque<>();
        this.getOrderPositions().push(tempId);
        this.productType = tempId--;
    }

    public Deque<Integer> getOrderPositions() {
        return this.orderPositions;
    }

    private Integer peekNextPosition() {
        if (this.getOrderPositions().isEmpty()) {
            this.getOrderPositions().push(tempId--);
        }

        return this.getOrderPositions().peek();
    }

    @Override
    public int compareTo(Order other) {
        return this.peekNextPosition().compareTo(other.peekNextPosition());
    }
}
