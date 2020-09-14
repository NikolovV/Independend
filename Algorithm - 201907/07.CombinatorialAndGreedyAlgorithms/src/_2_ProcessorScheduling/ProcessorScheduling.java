package _2_ProcessorScheduling;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessorScheduling {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int task = Integer.parseInt(in.nextLine().substring(7));

        Map<Integer, Integer> times = new LinkedHashMap<>();
        for (int i = 0; i < task; i++) {
            String[] currentElement = in.nextLine().split(" - ");
            times.put(Integer.parseInt(currentElement[0]), Integer.parseInt(currentElement[1]));
        }

        processorScheduling(times);
    }

    private static void processorScheduling(Map<Integer, Integer> times) {
        Map<Integer, Integer> resultTimes = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Integer> timesCopy = new TreeMap<>(Collections.reverseOrder());
        timesCopy.putAll(times);

        List<Integer> keySetIndex = new ArrayList<>(times.keySet());

        int totalSum = 0;
        Map.Entry<Integer, Integer> maxDeadline = Collections.max(times.entrySet(), Map.Entry.comparingByValue());
        for (int i = 1; i <= maxDeadline.getValue(); i++) {
            Map.Entry<Integer, Integer> maxValue = timesCopy.entrySet().iterator().next();
            resultTimes.put(maxValue.getKey(), maxValue.getValue());
            totalSum += maxValue.getKey();

            timesCopy.remove(maxValue.getKey());
        }

        resultTimes = resultTimes.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        printResult(resultTimes, keySetIndex, totalSum);
    }

    private static void printResult(Map<Integer, Integer> resultTimes, List<Integer> keySetIndex, int totalSum) {
        System.out.print("Optimal schedule: ");
        System.out.println(resultTimes.keySet()
                .stream()
                .map(e -> keySetIndex.indexOf(e) + 1)
                .map(Object::toString)
                .collect(Collectors.joining(" -> ")));
        System.out.printf("Total value: %d", totalSum);
    }

}
