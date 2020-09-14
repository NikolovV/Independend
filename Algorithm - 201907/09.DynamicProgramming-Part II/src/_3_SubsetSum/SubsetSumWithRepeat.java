package _3_SubsetSum;

public class SubsetSumWithRepeat {
    public static void main(String[] args) {
        int[] numbers = new int[]{3, 5, 2};
        int targetSum = 6;
        boolean[] possibleSum = new boolean[targetSum + 1];
        possibleSum[0] = true;

        for (int sum = 0; sum < possibleSum.length; sum++) {
            if (possibleSum[sum]) {
                for (int i = 0; i < numbers.length; i++) {
                    int newSum = sum + numbers[i];
                    if (newSum <= targetSum) {
                        possibleSum[newSum] = true;
                    }
                }
            }
        }
        System.out.println(possibleSum[targetSum]);

        while (targetSum != 0) {
            for (int i = 0; i < numbers.length; i++) {
                int sum = targetSum - numbers[i];
                if (sum >= 0 && possibleSum[sum]) {
                    System.out.print(numbers[i] + " ");
                    targetSum = sum;
                }
            }
        }
    }
}
