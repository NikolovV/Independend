package _9_HeapsAndPriorityQueue.Lab;

public class Heap {

    public static <E extends Comparable<E>> void sort(E[] array) {
        int length = array.length;

        for (int i = length / 2; i >= 0; i--) {
            //heapifyDown(array, i, length);
            heapifyDownRecursive(array, i, length);
        }

        for (int i = length - 1; i > 0; i--) {
            swap(array, 0, i);
            //heapifyDown(array, 0, i);
            heapifyDownRecursive(array, 0, i);
        }
    }

    private static <E extends Comparable<E>> void heapifyDown(E[] array, int index, int length) {
        while (index < length / 2) {
            int childIndex = (2 * index) + 1;

            if (childIndex + 1 < length && isLess(array, childIndex, childIndex + 1)) {
                childIndex++;
            }

            if (isLess(array, childIndex, index)) {
                break;
            }

            swap(array, childIndex, index);
            index = childIndex;
        }
    }

    private static <E extends Comparable<E>> void heapifyDownRecursive(E[] array, int index, int length) {
        if (index < length / 2) {
            int childIndex = (2 * index) + 1;

            if (childIndex + 1 < length && isLess(array, childIndex, childIndex + 1)) {
                childIndex++;
            }

            if (isLess(array, childIndex, index)) {
                return;
            }

            swap(array, childIndex, index);
            heapifyDownRecursive(array, childIndex, length);
        }
    }

    private static <E extends Comparable<E>> boolean isLess(E[] array, int parentIndex, int childIndex) {
        return array[parentIndex].compareTo(array[childIndex]) < 0;
    }

    private static <E> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
