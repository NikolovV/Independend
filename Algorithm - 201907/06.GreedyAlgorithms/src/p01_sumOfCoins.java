import java.util.*;

///p01_sumOfCoins
class p01_sumOfCoins {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));

        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
        print(usedCoins);
    }

    public static void print(Map<Integer, Integer> usedCoins) {
        Map<Integer, Integer> sorted = new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(usedCoins);
        int allCoins = 0;
        for (Integer s : sorted.values()) {
            allCoins += s;
        }

        System.out.printf("Number of coins to take: %d\n", allCoins);
        for (Map.Entry<Integer, Integer> a : sorted.entrySet()) {
            System.out.printf("%d coin(s) with value %d\n", a.getValue(), a.getKey());
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        Map<Integer, Integer> coinsUsed = new HashMap<>();

        coins = Arrays.stream(coins).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        int i = 0;
        int currentSum = 0;
        while (targetSum > 0) {
            while (i < coins.length) {
                int coinCnt = targetSum / coins[i];
                if (coinCnt == 0) {
                    i++;
                } else {
                    targetSum -= coins[i] * coinCnt;
                    coinsUsed.put(coins[i], coinCnt);
                }
            }
        }
        return coinsUsed;
    }
}
