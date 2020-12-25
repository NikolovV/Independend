package _14_AvlAndAaTreesRopeAndTrie_Exercise._1_FirstLastList.tests;

import _14_AvlAndAaTreesRopeAndTrie_Exercise._1_FirstLastList.FirstLastList;
import _14_AvlAndAaTreesRopeAndTrie_Exercise._1_FirstLastList.IFirstLastList;

public class FirstLastListFactory {
    public static <T extends Comparable<T>> IFirstLastList<T> create() {
        return new FirstLastList<T>();
    }
}
