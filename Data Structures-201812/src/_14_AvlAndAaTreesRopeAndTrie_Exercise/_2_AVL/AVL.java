package _14_AvlAndAaTreesRopeAndTrie_Exercise._2_AVL;

import java.util.ArrayList;
import java.util.List;
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
        } else {
            Node<T> newNode = new Node<>(item);
            Node<T> current = node;
            while (current.down != null) {
                current = current.down;
            }
            current.down = newNode;
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

    public List<T> min(int count) {
        List<T> minList = new ArrayList<>();
        this.min(this.root, minList, count);
        return minList;
    }

    private void min(Node<T> node, List<T> minList, int count) {
        if (node == null) {
            return;
        }
        this.min(node.left, minList, count);
        Node<T> current = node;
        while (current != null && minList.size() < count) {
            minList.add(current.value);
            current = current.down;
        }
        this.min(node.right, minList, count);
    }

    public List<T> max(int count) {
        List<T> maxList = new ArrayList<>();
        this.max(this.root, maxList, count);
        return maxList;
    }

    private void max(Node<T> node, List<T> maxList, int count) {
        if (node == null) {
            return;
        }
        this.max(node.right, maxList, count);
        Node<T> current = node;
        while (current != null && maxList.size() < count) {
            maxList.add(current.value);
            current = current.down;
        }
        this.min(node.left, maxList, count);
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

    public void delete(T element) {
        this.root = this.delete(this.root, element);
    }

    private Node<T> delete(Node<T> node, T element) {
        if (node == null) {
            return null;
        }

        int cmp = element.compareTo(node.value);
        if (cmp < 0) {
            node.left = this.delete(node.left, element);
        } else if (cmp > 0) {
            node.right = this.delete(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<T> minNode = this.findSmallestNode(node.right);
                minNode.right = this.deleteMin(node.right);
                minNode.left = node.left;
                node = minNode;
            }
        }

        node = this.balanceAVL(node);
        this.updateHeight(node);
        return node;
    }

    public void deleteMin() {
        if (this.root == null) {
            return;
        }
        this.root = this.deleteMin(this.root);
    }

    public Node<T> deleteMin(Node<T> current) {
        if (current.left == null) {
            return current.right;
        }
        current.left = this.deleteMin(current.left);
        current = this.balanceAVL(current);
        this.updateHeight(current);
        return current;
    }

    private Node<T> findSmallestNode(Node<T> node) {
        Node<T> current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void deleteMax() {
        if (this.root == null) {
            return;
        }

        this.root = this.deleteMax(this.root);
    }

    private Node<T> deleteMax(Node<T> current) {
        if (current.right == null) {
            return current.left;
        }

        current.right = this.deleteMin(current.right);
        current = this.balanceAVL(current);
        this.updateHeight(current);

        return current;
    }
}
