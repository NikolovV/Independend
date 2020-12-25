package _6_BasicTreeDataStructures.Labs._1_TreesAndTraversals;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private final T value;
    private final List<Tree<T>> children;

    @SafeVarargs
    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
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

    private void dfs(Tree<T> tTree, List<T> result) {
        for (Tree<T> node : tTree.children) {
            this.dfs(node, result);
        }
        result.add(tTree.value);
    }

    public Iterable<T> orderBFS() {
        List<T> result = new ArrayList<>();
        Queue<Tree<T>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<T> node = queue.poll();
            result.add(node.value);

            for (Tree<T> child : node.children) {
                queue.offer(child);
            }
        }

        return result;
    }

}