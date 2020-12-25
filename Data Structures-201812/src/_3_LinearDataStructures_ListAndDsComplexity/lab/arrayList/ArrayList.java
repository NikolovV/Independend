package _3_LinearDataStructures_ListAndDsComplexity.lab.arrayList;

import java.util.Arrays;

public class ArrayList<T> {
    private int maxCapacity;
    private static int numberElement;
    private T[] items;

    public ArrayList(int capacity) {
        this.items = (T[]) new Object[capacity];
        this.maxCapacity = capacity;
        numberElement = 0;
    }

    public ArrayList() {
        this(2);
    }

    public static int getCount() {
        return numberElement;
    }

    public T get(int index) {
        if (index < 0 || index >= this.maxCapacity) {
            throw new IllegalArgumentException();
        }
        return this.items[index];
    }

    public void add(T element) {
        if (numberElement >= this.maxCapacity) {
            this.grow();
        }
        this.items[numberElement++] = element;
    }

    private void grow() {
        this.maxCapacity *= 2;
        T[] newArr = Arrays.copyOf(this.items, this.maxCapacity);
        this.items = newArr;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= numberElement) {
            throw new IllegalArgumentException();
        }

        this.shiftLeft(index);
        if (numberElement-- < this.maxCapacity / 3) {
            this.shrink();
        }
        return this.items[index];
    }

    private void shrink() {
        this.maxCapacity /= 2;
        this.items = Arrays.copyOf(this.items, this.maxCapacity);
    }

    private void shiftLeft(int index) {
        if (numberElement - 1 - index >= 0) {
            System.arraycopy(this.items, index + 1, this.items, index, numberElement - 1 - index);
        }
        this.items[numberElement - 1] = null;
    }

    public void set(int i, T item) {
        if (i < 0 || i >= numberElement) {
            throw new IllegalArgumentException();
        }
        this.items[i] = item;
    }

}
