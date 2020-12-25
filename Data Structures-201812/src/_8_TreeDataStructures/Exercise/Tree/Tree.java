package _8_TreeDataStructures.Exercise.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Tree<T> {
    private final T value;
    private final List<Tree<T>> children;
    private Tree<T> parent;

    @SafeVarargs
    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        for (Tree<T> child : children) {
            this.addChild(child);
        }
    }

    public void addChild(Tree<T> element) {
        this.children.add(element);
        element.setParent(this);
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : this.children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new ArrayList<>();
        this.dfs(this, result);
        return result;
    }

    private void DFS(List<T> result) {
        for (Tree<T> child : this.children) {
            child.DFS(result);
        }
        result.add(this.getValue());
    }

    public T getValue() {
        return this.value;
    }

    public List<Tree<T>> getChildren() {
        return this.children;
    }

    private void dfs(Tree<T> tTree, List<T> result) {
        for (Tree<T> node : tTree.children) {
            this.dfs(node, result);
        }
        result.add(tTree.value);
    }

    public Tree<T> getParent() {
        return this.parent;
    }

    private void setParent(Tree<T> tTree) {
        this.parent = tTree;
    }

    public String print(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent * 2]).replace("\0", " "))
                .append(this.value)
                .append("\n");
        for (Tree<T> child : this.children) {
            child.print(indent + 1, builder);
        }

        return builder.toString();
    }
}
