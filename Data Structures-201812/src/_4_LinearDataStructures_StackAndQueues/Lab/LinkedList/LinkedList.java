package _4_LinearDataStructures_StackAndQueues.Lab.LinkedList;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private int size;

    private Node<E> head;
    private Node<E> tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        Node<E> newHead = new Node<>(item);
        if (this.size == 0) {
            this.head = this.tail = newHead;
        } else {
            newHead.next = this.head;
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(E item) {
        Node<E> newTail = new Node<>(item);
        if (this.size == 0) {
            this.head = this.tail = newTail;
        } else {
            this.tail.next = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public E removeFirst() {
        if (this.size <= 0) {
            throw new IllegalArgumentException();
        }

        E firstElement = this.head.value;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
        }
        this.size--;
        return firstElement;
    }

    public E removeLast() {
        if (this.size <= 0) {
            throw new IllegalArgumentException();
        }

        E lastElement = this.tail.value;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node<E> current = this.head;
            while (current.next != this.tail) {
                current = current.next;
            }
            current.next = null;
            this.tail = current;
        }
        this.size--;
        return lastElement;
    }

    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        Node<E> currentListNode = this.head;

        for (int i = 0; i < this.size; i++) {
            array[i] = currentListNode.value;
            currentListNode = currentListNode.next;
        }

        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements Iterator<E> {
        private Node<E> currentListNode;

        public LinkedListIterator() {
            this.currentListNode = LinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return this.currentListNode == LinkedList.this.tail;

        }

        @Override
        public E next() {
            E value = this.currentListNode.value;
            this.currentListNode = this.currentListNode.next;
            return value;
        }
    }

    private class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
