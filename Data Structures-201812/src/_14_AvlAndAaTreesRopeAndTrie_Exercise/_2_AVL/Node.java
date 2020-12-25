package _14_AvlAndAaTreesRopeAndTrie_Exercise._2_AVL;

public class Node<T extends Comparable<T>> {

    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node<T> down;

    public int height;

    public Node(T value) {
        this.value = value;
        this.height = 1;
    }

}
