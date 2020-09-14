package _1_ArraySum;

import java.util.Arrays;
import java.util.Scanner;

public class ArraySum {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String inputString = in.nextLine();

		int[] numArr;
		numArr = Arrays.asList(inputString.split(" ")).stream().mapToInt(Integer::parseInt).toArray();

		int sum = sumArray(numArr, 0);

		System.out.println(sum);
		in.close();
	}

	public static int sumArray(int[] arr, int index) {
		if (index == arr.length) {
			return 0;
		}

		return arr[index] + sumArray(arr, index + 1);

	}

}
