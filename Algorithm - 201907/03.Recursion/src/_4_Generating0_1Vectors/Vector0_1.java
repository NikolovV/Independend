package _4_Generating0_1Vectors;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector0_1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];

		vectorPrint(arr, 0);

		in.close();
	}

	public static void vectorPrint(int[] arr, int index) {
		if (index > arr.length - 1) {
			System.out.println(IntStream.of(arr).mapToObj(Integer::toString).collect(Collectors.joining("")));
			return;
		}

		for (int i = 0; i <= 1; i++) {
			arr[index] = i;
			vectorPrint(arr, index + 1);
		}
	}
}

