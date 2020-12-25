package _15_QuadTrees_K_DTrees_IntervalTrees._2_K_DTree;

import java.util.function.Consumer;

public class KdTree {

    private static final int K = 2;
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public boolean contains(Point2D point) {
        return this.root != null && this.contains(this.root, point, 0);
    }

    private boolean contains(Node node, Point2D point, int depth) {
        int compare = KdTree.compare(point, node.getPoint2D(), depth);
        if (compare == 0) {
            return true;
        }

        if (compare < 0) {
            return this.contains(node.getLeft(), point, depth + 1);
        } else {
            return this.contains(node.getRight(), point, depth + 1);
        }
    }

    public void insert(Point2D point) {
        this.root = this.insert(this.root, point, 0);
    }

    private Node insert(Node node, Point2D point, int depth) {
        if (node == null) {
            return new Node(point);
        }

        int compare = KdTree.compare(point, node.getPoint2D(), depth);

        if (compare < 0) {
            node.setLeft(this.insert(node.getLeft(), point, depth + 1));
        } else if (compare > 0) {
            node.setRight(this.insert(node.getRight(), point, depth + 1));
        }

        return node;
    }

    public void eachInOrder(Consumer<Point2D> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<Point2D> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getPoint2D());
        this.eachInOrder(node.getRight(), consumer);
    }

    private static int compare(Point2D a, Point2D b, int dept) {
        int cmp;
        if (dept % K == 0) {
            cmp = Double.compare(a.getX(), b.getX());
            if (cmp == 0) {
                cmp = Double.compare(a.getY(), b.getY());
            }
            return cmp;
        }

        cmp = Double.compare(a.getY(), b.getY());
        if (cmp == 0) {
            cmp = Double.compare(a.getX(), b.getX());
        }
        return cmp;
    }

    public static class Node {

        private Point2D point2D;
        private Node left;
        private Node right;

        public Node(Point2D point) {
            this.setPoint2D(point);
        }

        public Point2D getPoint2D() {
            return this.point2D;
        }

        public void setPoint2D(Point2D point2D) {
            this.point2D = point2D;
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
