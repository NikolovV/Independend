package _6_BasicTreeDataStructures.Labs._1_TreesAndTraversals;

import java.util.function.Consumer;

public class BinaryTree<T> {
    private final T value;
    private final BinaryTree<T> leftChild;
    private final BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this(value, null, null);
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this(value, child, null);
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // Pre-order = root -> left -> right
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent * 2]).replace("\0", " "))
                .append(this.value)
                .append("\n");

        if (this.leftChild != null) {
            this.leftChild.printIndentedPreOrder(indent + 1, builder);
        }

        if (this.rightChild != null) {
            this.rightChild.printIndentedPreOrder(indent + 1, builder);
        }
        return builder.toString();
    }

    // In-order = left -> root -> right
    public void eachInOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }

        consumer.accept(this.value);

        if (this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    // Post-order = left -> right -> root
    public void eachPostOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }

        if (this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }

        consumer.accept(this.value);
    }
}
