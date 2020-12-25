package _7_BinarySearchTrees;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeTests {

    private BinarySearchTree<Integer> bst;

    @Before
    public void setup() {
        this.bst = new BinarySearchTree<>();

        this.bst.insert(12);
        this.bst.insert(21);
        this.bst.insert(5);
        this.bst.insert(1);
        this.bst.insert(8);
        this.bst.insert(18);
        this.bst.insert(23);
    }

    @Test
    public void createBinaryTree_testStructure_leftSubTree() {
        BinarySearchTree<Integer>.Node root = this.bst.getRoot();

        Assert.assertEquals(Integer.valueOf(12), root.getValue());

        BinarySearchTree<Integer>.Node left = root.getLeft();

        Assert.assertEquals(Integer.valueOf(5), left.getValue());

        BinarySearchTree<Integer>.Node left_left = left.getLeft();
        BinarySearchTree<Integer>.Node left_right = left.getRight();

        Assert.assertEquals(Integer.valueOf(1), left_left.getValue());
        Assert.assertEquals(Integer.valueOf(8), left_right.getValue());
    }

    @Test
    public void createBinaryTree_testStructure_rightSubTree() {
        BinarySearchTree<Integer>.Node root = this.bst.getRoot();

        Assert.assertEquals(Integer.valueOf(12), root.getValue());

        BinarySearchTree<Integer>.Node right = root.getRight();

        Assert.assertEquals(Integer.valueOf(21), right.getValue());

        BinarySearchTree<Integer>.Node right_left = right.getLeft();
        BinarySearchTree<Integer>.Node right_right = right.getRight();

        Assert.assertEquals(Integer.valueOf(18), right_left.getValue());
        Assert.assertEquals(Integer.valueOf(23), right_right.getValue());
    }

    @Test
    public void testContainsMethod_shouldReturnFalse() {
        boolean contains = this.bst.contains(-10);
        Assert.assertEquals(false, contains);
    }

    @Test
    public void testContainsMethod_shouldReturnTrue() {
        boolean contains = this.bst.contains(-10);
        Assert.assertEquals(false, contains);
    }

    @Test
    public void testSearch_CheckReturnedTreeStructure1() {
        BinarySearchTree<Integer> search = this.bst.search(5);

        BinarySearchTree<Integer>.Node root = search.getRoot();
        Assert.assertEquals(Integer.valueOf(5), root.getValue());

        BinarySearchTree<Integer>.Node left = root.getLeft();
        BinarySearchTree<Integer>.Node right = root.getRight();

        Assert.assertEquals(Integer.valueOf(1), left.getValue());
        Assert.assertEquals(Integer.valueOf(8), right.getValue());
    }

    @Test
    public void testSearch_CheckReturnedTreeStructure2() {
        this.bst.insert(50);
        this.bst.insert(100);
        this.bst.insert(75);
        this.bst.insert(60);

        BinarySearchTree<Integer> search = this.bst.search(23);

        BinarySearchTree<Integer>.Node root = search.getRoot();
        Assert.assertEquals(Integer.valueOf(23), root.getValue());

        BinarySearchTree<Integer>.Node left = root.getLeft();
        BinarySearchTree<Integer>.Node right = root.getRight();

        Assert.assertEquals(null, left);
        Assert.assertEquals(Integer.valueOf(50), right.getValue());

        BinarySearchTree<Integer>.Node right_left = right.getLeft();
        BinarySearchTree<Integer>.Node right_right = right.getRight();

        Assert.assertEquals(null, right_left);
        Assert.assertEquals(Integer.valueOf(100), right_right.getValue());

        BinarySearchTree<Integer>.Node right_right_left = right_right.getLeft();
        BinarySearchTree<Integer>.Node right_right_right = right_right.getRight();

        Assert.assertEquals(Integer.valueOf(75), right_right_left.getValue());
        Assert.assertEquals(null, right_right_right);

        BinarySearchTree<Integer>.Node right_right_left_left = right_right_left.getLeft();
        Assert.assertEquals(Integer.valueOf(60), right_right_left_left.getValue());
    }

    @Test
    public void testRange_getAllNodes() {
        Iterable<Integer> range = this.bst.range(0, 100);

        Integer[] values = new Integer[]{1, 5, 8, 12, 18, 21, 23};
        int index = 0;
        for (Integer integer : range) {
            Assert.assertEquals(values[index++], integer);
        }
    }

    @Test
    public void testRange_shouldNotFindAnyNodes() {
        Iterable<Integer> range = this.bst.range(-1, -1);

        List<Integer> foundValues = new ArrayList<>();
        for (Integer value : range) {
            foundValues.add(value);
        }

        Assert.assertEquals(0, foundValues.size());
    }

    @Test
    public void testRange_shouldFindOnlyOneElement() {
        Iterable<Integer> range = this.bst.range(12, 12);

        int index = 0;
        Integer[] values = new Integer[]{12};
        for (Integer value : range) {
            Assert.assertEquals(values[index], value);
        }
    }

    @Test
    public void testRange_shouldFindNodes() {
        Iterable<Integer> range = this.bst.range(5, 18);

        int index = 0;
        Integer[] values = new Integer[]{5, 8, 12, 18};
        for (Integer value : range) {
            Assert.assertEquals(values[index++], value);
        }
    }
}
