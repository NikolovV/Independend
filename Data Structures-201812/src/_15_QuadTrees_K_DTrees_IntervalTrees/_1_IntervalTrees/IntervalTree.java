package _15_QuadTrees_K_DTrees_IntervalTrees._1_IntervalTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntervalTree {
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void insert(double lo, double hi) {
        this.root = this.insert(this.root, lo, hi);
    }

    private Node insert(Node node, double lo, double hi) {
        if (node == null) {
            return new Node(new Interval(lo, hi));
        }

        int cmp = Double.compare(lo, node.interval.getLo());
        if (cmp < 0) {
            node.left = this.insert(node.left, lo, hi);
        } else if (cmp > 0) {
            node.right = this.insert(node.right, lo, hi);
        }

        return node;
    }

    private void updateMax(Node node) {
        Node maxChild = this.getMaxChild(node.left, node.right);
        node.max = this.getMaxChild(node, maxChild).max;
    }

    private Node getMaxChild(Node left, Node right) {
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return right.max > left.max ? right : left;
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double lo, double hi) {
        Node node = this.root;
        while (node != null && !node.interval.intersects(lo, hi)) {
            if (node.left != null && node.left.max > lo) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (node == null) {
            return null;
        }

        return node.interval;
    }

    public Iterable<Interval> searchAll(double lo, double hi) {
        List<Interval> result = new ArrayList<>();
        this.searchAll(this.root, lo, hi, result);
        return result;
    }

    private void searchAll(Node node, double lo, double hi, List<Interval> intervals) {
        if (node == null) {
            return;
        }

        boolean goLeft = node.left != null && node.left.max > lo;
        boolean goRight = node.right != null && node.right.interval.getLo() < hi;

        if (goLeft) {
            this.searchAll(node.left, lo, hi, intervals);
        }

        if (node.interval.intersects(lo, hi)) {
            intervals.add(node.interval);
        }

        if (goRight) {
            this.searchAll(node.right, lo, hi, intervals);
        }
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private class Node {

        private Interval interval;
        private double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getHi();
        }
    }
}
