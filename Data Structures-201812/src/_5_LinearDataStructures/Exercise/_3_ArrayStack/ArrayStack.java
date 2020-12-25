package _5_LinearDataStructures.Exercise._3_ArrayStack;

public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.size = 0;
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        this.elements[this.size++] = element;
        if (this.elements.length <= this.size) {
            this.grow();
        }
    }

    public T pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Stack is empty");
        }

        this.size--;
        T element = this.elements[this.size];
        this.elements[this.size] = null;
        return element;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            array[i] = this.elements[this.size - 1 - i];
        }
        return array;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[this.size * 2];
        if (this.size >= 0) {
            System.arraycopy(this.elements, 0, newArray, 0, this.size);
        }
        this.elements = newArray;
    }
}