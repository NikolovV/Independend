package _5_Combinations;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Combinations {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String input = in.nextLine();
		int[] numArr = Arrays.asList(input.split(" ")).stream().mapToInt(Integer::parseInt).toArray();

		int n = in.nextInt();
		int[] pair = new int[n];

		generateCombinations(numArr, pair, 0, 0);

		in.close();
	}

	public static void generateCombinations(int[] arr, int[] pair, int pairIndex, int marker) {
		if (pairIndex >= pair.length) {
			System.out.println(IntStream.of(pair).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			return;
		}

		for (int i = marker; i < arr.length; i++) {
			pair[pairIndex] = arr[i];
			generateCombinations(arr, pair, pairIndex + 1, i + 1);
		}
	}
}
