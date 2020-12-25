package _3_LinearDataStructures_ListAndDsComplexity.Exercise._8_DoublyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int maxCapacity;
    private ListNode<T> head;
    private ListNode<T> tail;

    public int size() {
        return this.maxCapacity;
    }

    public void addFirst(T element) {
        if (this.maxCapacity == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newHead = new ListNode<>(element);
            newHead.next = this.head;
            this.head.prev = newHead;
            this.head = newHead;
        }
        this.maxCapacity++;
    }

    public void addLast(T element) {
        if (this.maxCapacity == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newTail = new ListNode<>(element);
            newTail.prev = this.tail;
            this.tail.next = newTail;
            this.tail = newTail;
        }
        this.maxCapacity++;
    }

    public T removeFirst() {
        if (this.maxCapacity <= 0) {
            throw new IllegalArgumentException("List is empty");
        }

        T firstElement = this.head.value;
        this.head = this.head.next;

        if (this.head != null) {
            this.head.prev = null;
        } else {
            this.tail = null;
        }
        this.maxCapacity--;

        return firstElement;
    }

    public T removeLast() {
        if (this.maxCapacity <= 0) {
            throw new IllegalArgumentException("List is empty");
        }

        T lastElement = this.tail.value;
        this.tail = this.tail.prev;

        if (this.tail != null) {
            this.tail.next = null;
        } else {
            this.head = null;
        }
        this.maxCapacity--;

        return lastElement;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[this.maxCapacity];
        ListNode<T> currentListNode = this.head;

        for (int i = 0; i < this.maxCapacity; i++) {
            array[i] = currentListNode.value;
            currentListNode = currentListNode.next;
        }

        return array;
    }

    private class ListNode<E> {
        private final E value;
        private ListNode<E> next;
        private ListNode<E> prev;

        public ListNode(E value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

        for (T t : this) {
            action.accept(t);
        }
    }

    class ListIterator implements Iterator<T> {
        ListNode<T> currentListNode = DoublyLinkedList.this.head;

        @Override
        public boolean hasNext() {
            return this.currentListNode != null;

        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            T element = this.currentListNode.value;
            this.currentListNode = this.currentListNode.next;

            return element;
        }
    }
}
