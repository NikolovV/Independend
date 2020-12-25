package _5_LinearDataStructures.Exercise._4_LinkedStack;

public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        if (this.firstNode == null) {
            this.firstNode = new Node<>(element);
        } else {
            this.firstNode = new Node<>(element, this.firstNode);
        }
        
        this.size++;
    }

    public E pop() {
        if (this.firstNode == null) {
            throw new IllegalArgumentException("Stack is empty");
        } else {
            E nodeValue = this.firstNode.value;
            this.firstNode = this.firstNode.nextNode;
            this.size -= 1;
            return nodeValue;
        }
    }

    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        Node<E> currentElement = this.firstNode;
        for (int i = 0; i < this.size; i++) {
            array[i] = currentElement.value;
            currentElement = currentElement.nextNode;
        }
        return array;
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}