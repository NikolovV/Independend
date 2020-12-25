package _7_BinarySearchTrees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node node) {
        this.copy(node);
    }

    private void copy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.copy(node.left);
        this.copy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        Node newNode = new Node(value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public boolean contains(T value) {
        Node current = this.root;

        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public void eachInOrder(Node node, Consumer<T> consumer) {
        if (node.left != null) {
            this.eachInOrder(node.left, consumer);
        }

        consumer.accept(node.value);

        if (node.right != null) {
            this.eachInOrder(node.right, consumer);
        }
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node parent = null;
        Node minNode = this.root;
        while (minNode.left != null) {
            parent = minNode;
            minNode = minNode.left;
        }

        if (parent == null) {
            this.root = minNode.right;
        } else {
            parent.left = minNode.right;
        }
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();

        this.range(this.root, queue, from, to);

        return queue;
    }

    private void range(Node node, Queue<T> queue, T from, T to) {
        if (node == null) {
            return;
        }
        int nodeInLowerRange = from.compareTo(node.value);
        int nodeInUpperRange = to.compareTo(node.value);
        if (nodeInLowerRange < 0) {
            this.range(node.left, queue, from, to);
        }
        if (nodeInLowerRange <= 0 && nodeInUpperRange >= 0) {
            queue.offer(node.value);
        }
        if (nodeInUpperRange > 0) {
            this.range(node.right, queue, from, to);
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

