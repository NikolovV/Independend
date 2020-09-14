package _4_TowerofHanoi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TowerofHanoi {
    public static Stack<Integer> source;
    public static Stack<Integer> spare = new Stack<>();
    public static Stack<Integer> target = new Stack<>();
    public static int stepCount = 0;

    public static void main(String[] args) {
//        long startTime = System.nanoTime();

        Scanner in = new Scanner((System.in));
        int diskNumber = 3; //in.nextInt();

        List<Integer> lis = IntStream.range(1, diskNumber + 1).boxed().collect(Collectors.toList());
        Collections.reverse(lis);
        source = new Stack<>();
        source.addAll(lis);

        printPegs();
        towerOfHanoi(diskNumber, source, target, spare);

//        long endTime = System.nanoTime();
//        System.out.println((endTime - startTime) / 1000000);
    }

    private static void towerOfHanoi(int diskSize, Stack<Integer> sourcePeg, Stack<Integer> targetPeg, Stack<Integer> sparePeg) {
        if (diskSize == 1) {
            stepCount++;
            targetPeg.add(sourcePeg.pop());
            System.out.println(String.format("Step #%d: Moved disk", stepCount));
            printPegs();
        } else {
            towerOfHanoi(diskSize - 1, sourcePeg, sparePeg, targetPeg);
            targetPeg.add(sourcePeg.pop());
            stepCount++;
            System.out.println(String.format("Step #%d: Moved disk", stepCount));
            printPegs();
            towerOfHanoi(diskSize - 1, sparePeg, targetPeg, sourcePeg);
        }
    }

//    private static void towerOfHanoi(int diskSize, Stack<Integer> sourcePeg, Stack<Integer> targetPeg, Stack<Integer> sparePeg) {
//        if (diskSize > 0) {
//            towerOfHanoi(diskSize - 1, sourcePeg, sparePeg, targetPeg);
//            targetPeg.add(sourcePeg.pop());
//            System.out.println(String.format("Step #%d: Moved disk", ++stepCount));
//            printPegs();
//            towerOfHanoi(diskSize - 1, sparePeg, targetPeg, sourcePeg);
//        }
//    }

    private static void printPegs() {
        System.out.println("Source: " + Arrays.stream(source.toArray()).map(String::valueOf).collect(Collectors.joining(", ")));
        System.out.println("Destination: " + Arrays.stream(target.toArray()).map(String::valueOf).collect(Collectors.joining(", ")));
        System.out.println("Spare: " + Arrays.stream(spare.toArray()).map(String::valueOf).collect(Collectors.joining(", ")));
        System.out.println();
    }

}
