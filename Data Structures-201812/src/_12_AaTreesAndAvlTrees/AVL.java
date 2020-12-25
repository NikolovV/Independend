package _12_AaTreesAndAvlTrees;

import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            node.left = this.insert(node.left, item);
        } else if (cmp > 0) {
            node.right = this.insert(node.right, item);
        }

        node = this.balanceAVL(node);
        this.updateHeight(node);

        return node;
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private void updateHeight(Node<T> node) {
        node.height = Math.max(this.height(node.left), this.height(node.right)) + 1;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.left;
        node.left = left.right;
        left.right = node;

        this.updateHeight(node);

        return left;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.right;
        node.right = right.left;
        right.left = node;

        this.updateHeight(node);

        return right;
    }

    private Node<T> balanceAVL(Node<T> node) {
        int balance = this.height(node.left) - this.height(node.right);
        if (balance < -1) {
            int childBalance = this.height(node.right.left) - this.height(node.right.right);
            if (childBalance > 0) {
                node.right = this.rotateRight(node.right);
            }
            return this.rotateLeft(node);
        } else if (balance > 1) {
            int childBalance = this.height(node.left.left) - this.height(node.left.right);
            if (childBalance < 0) {
                node.left = this.rotateLeft(node.left);
            }
            return this.rotateRight(node);
        }

        return node;
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return this.search(node.left, item);
        } else if (cmp > 0) {
            return this.search(node.right, item);
        }

        return node;
    }
}
