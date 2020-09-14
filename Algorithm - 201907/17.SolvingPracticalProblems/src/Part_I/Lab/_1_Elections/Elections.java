package Part_I.Lab._1_Elections;

import java.math.BigInteger;
import java.util.Scanner;

public class Elections {
    private static int seats;
    private static int parties;
    private static int[] election;
    private static int maxSeats = 0;

    public static void main(String[] args) {
        readInput();
        possibleCombo(calculateElection());
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);

        seats = Integer.parseInt(in.nextLine());
        parties = Integer.parseInt(in.nextLine());
        election = new int[parties];
        for (int i = 0; i < parties; i++) {
            election[i] = Integer.parseInt(in.nextLine());
            maxSeats += election[i];
        }
    }

    private static BigInteger[] calculateElection() {
        BigInteger[] sums = new BigInteger[maxSeats + 1];
        sums[0] = BigInteger.ONE;

        for (int seat : election) {
            for (int i = sums.length - 1; i >= 0; i--) {
                if (sums[i] != null && sums[i].intValue() != 0) {
                    BigInteger currentSum = sums[i + seat] == null ? BigInteger.ZERO : sums[i + seat];
                    sums[i + seat] = currentSum.add(sums[i]);
                }
            }
        }

        return sums;
    }

    private static void possibleCombo(BigInteger[] electionSum) {
        BigInteger count = BigInteger.ZERO;
        for (int i = seats; i < electionSum.length; i++) {
            BigInteger currentSum = electionSum[i] == null ? BigInteger.ZERO : electionSum[i];
            count = count.add(currentSum);
        }

        System.out.println(count);
    }
}
