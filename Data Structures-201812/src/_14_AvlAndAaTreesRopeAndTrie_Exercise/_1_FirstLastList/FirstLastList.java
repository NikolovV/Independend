package _14_AvlAndAaTreesRopeAndTrie_Exercise._1_FirstLastList;

import java.util.ArrayList;
import java.util.List;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private List<T> elements;
    private AVL<T> treeStructure;

    public FirstLastList() {
        this.elements = new ArrayList<>();
        this.treeStructure = new AVL<>();
    }

    @Override
    public void add(T element) {
        this.elements.add(element);
        this.treeStructure.insert(element);
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if (count > this.getCount()) {
            throw new IllegalArgumentException();
        }

        return new ArrayList<>(this.elements.subList(0, count));
    }

    @Override
    public Iterable<T> last(int count) {
        if (count > this.getCount()) {
            throw new IllegalArgumentException();
        }

        int toIndex = this.getCount() - count;
        List<T> sublist = new ArrayList<>();
        for (int i = this.getCount() - 1; i >= toIndex; i--) {
            sublist.add(this.elements.get(i));
        }
        return sublist;
    }

    @Override
    public Iterable<T> min(int count) {
        if (count > this.getCount()) {
            throw new IllegalArgumentException();
        }

        return this.treeStructure.min(count);
    }

    @Override
    public Iterable<T> max(int count) {
        if (count > this.getCount()) {
            throw new IllegalArgumentException();
        }

        return this.treeStructure.max(count);
    }

    @Override
    public void clear() {
        this.elements.clear();
        this.treeStructure = new AVL<>();
    }

    @Override
    public int removeAll(T element) {
        int count = 0;
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).compareTo(element) == 0) {
                this.elements.remove(i);
                i--;
                count++;
            }
        }

        if (count > 0) {
            this.treeStructure.delete(element);
        }

        return count;
    }
}