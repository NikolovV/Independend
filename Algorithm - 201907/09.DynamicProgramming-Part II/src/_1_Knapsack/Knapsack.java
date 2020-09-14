package _1_Knapsack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Knapsack {
    private static List<Item> itemList = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int knapsackCapacity = in.nextInt();
        in.nextLine();

        String[] line = in.nextLine().split(" ");
        while (line[0].compareTo("end") != 0) {
            itemList.add(new Item(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2])));
            line = in.nextLine().split(" ");
        }

        List<Item> collectItems = fillKnapsack(itemList, knapsackCapacity);

        System.out.println("Total Weight: " + collectItems.stream().map(i -> i.weight).reduce(0, Integer::sum));
        System.out.println("Total Value: " + collectItems.stream().map(i -> i.price).reduce(0, Integer::sum));
        collectItems.forEach(it -> System.out.println(it.name));
    }

    private static List<Item> fillKnapsack(List<Item> itemList, int knapsackCapacity) {
        int[][] maxValues = new int[itemList.size() + 1][knapsackCapacity + 1];
        boolean[][] includeItem = new boolean[itemList.size() + 1][knapsackCapacity + 1];

        for (int i = 0; i < itemList.size(); i++) {
            for (int currentCapacity = 1; currentCapacity <= knapsackCapacity; currentCapacity++) {
                if (itemList.get(i).weight > currentCapacity) {
                    continue;
                }

                int valueIncluded = itemList.get(i).price + maxValues[i][currentCapacity - itemList.get(i).weight];
                if (valueIncluded > maxValues[i][currentCapacity]) {
                    maxValues[i + 1][currentCapacity] = valueIncluded;
                    includeItem[i + 1][currentCapacity] = true;
                } else {
                    maxValues[i + 1][currentCapacity] = maxValues[i][currentCapacity];
                }
            }

        }

        List<Item> takenItems = new LinkedList<>();
        for (int i = itemList.size(); i > 0; --i) {
            if (!includeItem[i][knapsackCapacity]) {
                continue;
            }
            Item item = itemList.get(i - 1);
            takenItems.add(item);
            knapsackCapacity -= item.weight;
        }

        Collections.sort(takenItems);
        return takenItems;
    }

    private static class Item implements Comparable<Item> {
        public String name;
        public int weight;
        public int price;

        public Item(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Item o) {
            return this.name.compareTo(o.name);
        }
    }
}
