package _9_HeapsAndPriorityQueue.Lab;

import java.util.LinkedList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int size;

    public BinaryHeap() {
        this.heap = new LinkedList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public void insert(T element) {
        this.heap.add(element);
        //this.heapifyUp(this.size() - 1);
        this.heapifyUpRecursive(this.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0 && this.isLess(parent(index), index)) {
            this.swap(index, parent(index));
            index = parent(index);
        }
    }

    private void heapifyUpRecursive(int index) {
        if (index > 0 && this.isLess(parent(index), index)) {
            this.swap(index, parent(index));
            this.heapifyUpRecursive(parent(index));
        }
    }

    private boolean isLess(int parentIndex, int childIndex) {
        return this.heap.get(parentIndex).compareTo(this.heap.get(childIndex)) < 0;
    }

    private void swap(int index, int parent) {
        T temp = this.heap.get(index);
        this.heap.set(index, this.heap.get(parent));
        this.heap.set(parent, temp);
    }

    private static int parent(int index) {
        return (index - 1) / 2;
    }

    public T peek() {
        if (this.size() <= 0) {
            throw new IllegalArgumentException("Heap is empty!");
        }

        return this.heap.get(0);
    }

    public T pull() {
        if (this.size() <= 0) {
            throw new IllegalArgumentException("Heap is empty!");
        }

        T element = this.heap.get(0);

        this.swap(0, this.size() - 1);
        this.heap.remove(this.size() - 1);
        this.heapifyDown(0);

        return element;
    }

    private void heapifyDown(int index) {
        while (index < this.size() / 2) {
            int childIndex = (2 * index) + 1;

            if (childIndex + 1 < this.size() && this.isLess(childIndex, childIndex + 1)) {
                childIndex++;
            }

            if (this.heap.get(childIndex).compareTo(this.heap.get(index)) < 0) {
                break;
            }

            T temp = this.heap.get(index);
            this.heap.set(index, this.heap.get(childIndex));
            this.heap.set(childIndex, temp);
            index = childIndex;
        }
    }
}
