package _4_Words;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Words {
    private static int count;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] elements = in.nextLine().toCharArray();

        Set<Character> unique = CharBuffer.wrap(elements).chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet());

        if (unique.size() == elements.length) {
            count = 1;
            for (int i = 1; i <= elements.length; i++) {
                count *= i;
            }
        } else {
            permutate(elements, 0);
        }
        System.out.println(count);
    }

    private static void permutate(char[] arr, int index) {
        if (index >= arr.length) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == arr[i - 1]) {
                    return;
                }
            }
            count++;
        } else {
            HashSet<Character> swapped = new HashSet<>();
            for (int i = index; i < arr.length; i++) {
                if (!swapped.contains(arr[i])) {
                    swap(arr, i, index);
                    permutate(arr, index + 1);
                    swap(arr, index, i);
                    swapped.add(arr[i]);
                }
            }
        }

    }

    private static void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
