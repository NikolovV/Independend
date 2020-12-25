package _3_LinearDataStructures_ListAndDsComplexity.Exercise._6_ReversedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ReversedList<T> implements Iterable<T> {
    private int maxCapacity;
    private T[] items;
    private int numberItems;

    public ReversedList() {
        this.maxCapacity = 2;
        this.numberItems = 0;
        this.items = (T[]) new Object[this.maxCapacity];
    }

    public int count() {
        return this.numberItems;
    }

    public int capacity() {
        return this.maxCapacity;
    }

    public T get(int index) {
        if (index >= this.count() || index < 0) {
            throw new IllegalArgumentException();
        }

        return this.items[this.numberItems - 1 - index];
    }

    public void set(int index, T value) {
        if (index >= this.numberItems || index < 0) {
            throw new IllegalArgumentException();
        }

        this.items[this.numberItems - 1 - index] = value;
    }

    public void add(T item) {
        if (this.numberItems >= this.maxCapacity) {
            this.resize();
        }

        this.items[this.numberItems++] = item;
    }

    public T removeAt(int index) {
        if (index >= this.numberItems || index < 0) {
            throw new IllegalArgumentException();
        }

        int indexToRemove = this.numberItems - 1 - index;
        T element = this.items[indexToRemove];
        this.items[indexToRemove] = null;

        for (int i = indexToRemove + 1; i < this.items.length; i++) {
            this.items[i - 1] = this.items[i];
        }
        this.numberItems--;

        return element;
    }

    private void resize() {
        this.maxCapacity *= 2;
        T[] copy = (T[]) new Object[this.maxCapacity];

        for (int i = 0; i < this.items.length; i++) {
            copy[i] = this.items[i];
        }

        this.items = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator iterator = this.iterator();

        while (iterator.hasNext()) {
            action.accept((T) iterator.next());
        }
    }

    class ArrayIterator implements Iterator<T> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < ReversedList.this.numberItems;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            return ReversedList.this.items[this.current++];
        }
    }
}