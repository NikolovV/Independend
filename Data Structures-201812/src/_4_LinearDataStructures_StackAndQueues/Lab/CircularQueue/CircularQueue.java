package _4_LinearDataStructures_StackAndQueues.Lab.CircularQueue;

public class CircularQueue<E> {

    private int size;
    private static final int DEFAULT_CAPACITY = 4;
    private int startIndex = 0;
    private int endIndex = 0;
    private E[] elements;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
        this.size = 0;
        this.startIndex = 0;
        this.endIndex = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (this.size == this.elements.length) {
            this.grow();
        }

        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        E element = this.elements[this.startIndex];
        this.elements[this.startIndex] = null;
        this.startIndex = (this.startIndex + 1) % this.elements.length;
        if (this.size-- < this.elements.length / 3) {
            this.resize();
        }
        return element;
    }

    public E[] toArray() {
        E[] resultArray = (E[]) new Object[this.size];
        this.copyAllElements(resultArray);
        return resultArray;
    }

    private void resize() {
        E[] newArray = (E[]) new Object[this.elements.length / 2];
        this.copyAllElements(newArray);
        this.startIndex = 0;
        this.endIndex = this.size;
        this.elements = newArray;
    }

    private void grow() {
        E[] newArray = (E[]) new Object[this.elements.length * 2];
        this.copyAllElements(newArray);
        this.startIndex = 0;
        this.endIndex = this.size;
        this.elements = newArray;
    }

    private void copyAllElements(E[] resultArray) {
        int index = this.startIndex;
        for (int i = 0; i < this.size; i++) {
            resultArray[i] = this.elements[index];
            index = (index + 1) % this.elements.length;
        }
    }
}
