package _5_LinearDataStructures.Exercise._6_SequenceNM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class SequenceNM {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        if (n == m) {
            System.out.println(n);
            return;
        } else if (m <= n) {
            return;
        }

        Item firstItem = new Item(n, null);
        Deque<Item> queue = new ArrayDeque<>();
        queue.offer(firstItem);
        while (!queue.isEmpty()) {
            Item currentItem = queue.poll();
            int currentNum = currentItem.getValue();
            Item item1 = new Item(currentNum + 1, currentItem);
            Item item2 = new Item(currentNum + 2, currentItem);
            Item item3 = new Item(currentNum * 2, currentItem);
            if (item1.getValue() == m || item2.getValue() == m || item3.getValue() == m) {
                if (item1.getValue() == m) {
                    printSolution(item1);
                } else if (item2.getValue() == m) {
                    printSolution(item2);
                } else {
                    printSolution(item3);
                }
                break;
            }
            queue.offer(item1);
            queue.offer(item2);
            queue.offer(item3);
        }
    }

    private static void printSolution(Item item) {
        Deque<Integer> queue = new ArrayDeque<>();
        while (item != null) {
            queue.push(item.getValue());
            item = item.getPrev();
        }

        System.out.println(queue.stream().map(String::valueOf).collect(Collectors.joining(" -> ")));
    }
}

class Item {
    private final int value;
    private final Item prev;

    Item(int value, Item prev) {
        this.value = value;
        this.prev = prev;
    }

    public int getValue() {
        return this.value;
    }

    public Item getPrev() {
        return this.prev;
    }
}

