package _17_QuadKDIntervalTreesHashTables_Exercise._3_MassEffectGalaxyMap;

import java.util.ArrayList;
import java.util.List;

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

    public void insert(Point2D point, int galaxySize) {
        this.root = this.insert(this.root, point, 0, new GalaxyArea(0, 0, galaxySize, galaxySize));
    }

    private Node insert(Node node, Point2D point, int depth, GalaxyArea galaxyArea) {
        if (node == null) {
            return new Node(point, galaxyArea);
        }

        int compare = KdTree.compare(point, node.getPoint2D(), depth);

        if (compare < 0) {
            if (depth % K == 0) {
                galaxyArea.setX2((int) node.getPoint2D().getX());
            } else {
                galaxyArea.setY2((int) node.getPoint2D().getY());
            }
            node.setLeft(this.insert(node.getLeft(), point, depth + 1, galaxyArea));
        } else if (compare > 0) {
            if (depth % K == 0) {
                galaxyArea.setX((int) node.getPoint2D().getX());
            } else {
                galaxyArea.setY((int) node.getPoint2D().getY());
            }
            node.setRight(this.insert(node.getRight(), point, depth + 1, galaxyArea));
        }

        return node;
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

    public int findPointsInArea(GalaxyArea area) {
        List<Node> result = new ArrayList<>();
        KdTree.findPointsInArea(this.root, area, result);
        return result.size();
    }

    private static void findPointsInArea(Node node, GalaxyArea area, List<Node> result) {
        if (node.getPoint2D().isInside(area)) {
            result.add(node);
        }

        if (node.left != null && area.intersects(node.left.area)) {
            findPointsInArea(node.left, area, result);
        }

        if (node.right != null && area.intersects(node.right.area)) {
            findPointsInArea(node.right, area, result);
        }
    }

    public static class Node {
        private GalaxyArea area;
        private Point2D point2D;
        private Node left;
        private Node right;

        public Node(Point2D point, GalaxyArea galaxyArea) {
            this.setPoint2D(point);
            this.setArea(galaxyArea);
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

        public void setArea(GalaxyArea galaxyArea) {
            this.area = new GalaxyArea(galaxyArea.getX(), galaxyArea.getY(),
                    galaxyArea.getX2() - galaxyArea.getX(),
                    galaxyArea.getY2() - galaxyArea.getY());
        }
    }
}
