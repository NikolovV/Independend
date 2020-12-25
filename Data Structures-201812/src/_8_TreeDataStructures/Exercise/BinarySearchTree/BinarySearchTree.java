package _8_TreeDataStructures.Exercise.BinarySearchTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int count() {
        return this.count(this.root);
    }

    private int count(Node node) {
        if (node == null) {
            return 0;
        }

        return node.childrenCount;
    }

    public void insert(T value) {
        this.nodesCount++;

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            parent.childrenCount++;

            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
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

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node min = this.root;
        Node parent = null;

        while (min.left != null) {
            parent = min;
            parent.childrenCount--;
            min = min.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = min.right;
        }

        this.nodesCount--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node max = this.root;
        Node parent = null;

        while (max.right != null) {
            parent = max;
            parent.childrenCount--;
            max = max.right;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.right = max.left;
        }

        this.nodesCount--;
    }

    public T ceil(T element) {
        return this.select(this.rank(element) + 1);
//        if (this.root == null) {
//            return null;
//        }
//
//        T nearestLarger = this.root.value;
//        Node current = this.root;
//        while (current != null) {
//            if ((current.value.compareTo(element) > 0 && current.value.compareTo(nearestLarger) < 0) ||
//                    nearestLarger.compareTo(element) < 0) {
//                nearestLarger = current.value;
//            }
//
//            if (current.value.compareTo(element) > 0) {
//                current = current.left;
//            } else if (current.value.compareTo(element) < 0) {
//                current = current.right;
//            } else {
//                return current.value;
//            }
//        }
//
//        if (nearestLarger.compareTo(element) < 0) {
//            return null;
//        }
//        return nearestLarger;
    }

    public T floor(T element) {
        return this.select(this.rank(element) - 1);
//        if (this.root == null) {
//            return null;
//        }
//
//        T nearestSmaller = this.root.value;
//        Node current = this.root;
//        while (current != null) {
//            if ((current.value.compareTo(element) < 0 && current.value.compareTo(nearestSmaller) > 0) ||
//                    nearestSmaller.compareTo(element) > 0) {
//                nearestSmaller = current.value;
//            }
//
//            if (current.value.compareTo(element) > 0) {
//                current = current.left;
//            } else if (current.value.compareTo(element) < 0) {
//                current = current.right;
//            } else {
//                return current.value;
//            }
//        }
//
//        if (nearestSmaller.compareTo(element) > 0) {
//            return null;
//        }
//        return nearestSmaller;
    }

    public void delete(T key) {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        if (!this.contains(key)) {
            return;
        }

        this.root = this.delete(this.root, key);
    }

    private Node delete(Node node, T element) {
        if (node == null) {
            return null;
        }

        int compare = node.value.compareTo(element);

        if (compare > 0) {
            node.left = this.delete(node.left, element);
        } else if (compare < 0) {
            node.right = this.delete(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node leftmostNode = this.leftmostSubtree(node.right);
            node.value = leftmostNode.value;
            node.right = this.delete(node.right, leftmostNode.value);
        }

        node.childrenCount--;
        return node;
    }

    private Node leftmostSubtree(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public int rank(T item) {
        return this.rank(item, this.root);
    }

    private int rank(T item, Node node) {
        if (node == null) {
            return 0;
        }
        int compare = item.compareTo(node.value);

        if (compare > 0) {
            return 1 + this.rank(item, node.left) + this.rank(item, node.right);
        }

        return this.rank(item, node.left);
    }

    public T select(int n) {
        Node node = this.select(this.root, n);

        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node select(Node node, int rank) {
        if (node == null) {
            return null;
        }
        int leftCount = this.count(node.left);

        if (leftCount > rank) {
            return this.select(node.left, rank);
        } else if (leftCount < rank) {
            return this.select(node.right, rank - (leftCount + 1));
        }

        return node;
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
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

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}

