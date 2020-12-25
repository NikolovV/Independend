package _5_LinearDataStructures.Exercise._5_LinkedQueue;

public class LinkedQueue<E> {

    private int size;

    private QueueNode<E> headNode;
    private QueueNode<E> tailNode;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (this.headNode == null) {
            this.headNode = this.tailNode = new QueueNode<>();
            this.headNode.value = element;
        } else {
            QueueNode<E> newTail = new QueueNode<>();
            newTail.value = element;
            this.tailNode.nextNode = newTail;
            this.tailNode = newTail;
        }
        this.size++;
    }

    public E dequeue() {
        if (this.headNode == null) {
            this.tailNode = null;
            throw new IllegalArgumentException("Queue is empty");
        }
        E value = this.headNode.value;
        this.headNode = this.headNode.nextNode;
        this.size--;
        return value;
    }

    public E[] toArray() {
        QueueNode<E> currentNode = this.headNode;
        E[] array = (E[]) new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            array[i] = currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return array;
    }

    private class QueueNode<E> {
        private E value;

        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode<E> prevNode) {
            this.prevNode = prevNode;
        }
    }
}