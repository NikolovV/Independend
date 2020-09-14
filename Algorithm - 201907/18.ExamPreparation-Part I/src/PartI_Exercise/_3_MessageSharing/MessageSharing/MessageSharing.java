package PartI_Exercise._3_.MessageSharing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MessageSharing {
    private static final Map<String, List<String>> peopleGraph = new HashMap<>();
    private static final Map<String, Integer> peopleSteps = new HashMap<>();
    private static final Deque<String> messengers = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] persons = in.readLine().split(": ")[1].split(", ");
        for (String person : persons) {
            peopleGraph.putIfAbsent(person, new ArrayList<>());
            peopleSteps.putIfAbsent(person, -1);
        }

        String[] connections = in.readLine().split(": ")[1].split(", ");
        for (String connection : connections) {
            String[] currentConnection = connection.split(" - ");
            String firstPerson = currentConnection[0];
            String secondPerson = currentConnection[1];
            peopleGraph.get(firstPerson).add(secondPerson);
            peopleGraph.get(secondPerson).add(firstPerson);
        }

        String[] firstPeople = in.readLine().split(": ")[1].split(", ");
        for (String person : firstPeople) {
            messengers.offer(person);
            peopleSteps.put(person, 0);
        }

        int maxSteps = getMaxSteps();
        boolean isReceivedByAll = true;
        Set<String> lastPeople = new TreeSet<>();
        Set<String> unreachablePeople = new TreeSet<>();

        for (Map.Entry<String, Integer> pair : peopleSteps.entrySet()) {
            if (pair.getValue() == -1) {
                unreachablePeople.add(pair.getKey());
                isReceivedByAll = false;
            } else if (isReceivedByAll && pair.getValue() == maxSteps) {
                lastPeople.add(pair.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();

        if (!isReceivedByAll) {
            sb.append(String.format("Cannot reach: %s", unreachablePeople.stream().map(Object::toString).collect(Collectors.joining(", "))));
        } else {
            sb.append(String.format("All people reached in %d steps%n", maxSteps))
                    .append(String.format("People at last step: %s", lastPeople.stream().map(Object::toString).collect(Collectors.joining(", "))));
        }
        System.out.print(sb);
    }

    private static int getMaxSteps() {
        int maxSteps = 0;

        while (!messengers.isEmpty()) {
            String currentPerson = messengers.poll();

            for (String child : peopleGraph.get(currentPerson)) {
                if (peopleSteps.get(child) == -1) {
                    messengers.offer(child);
                    int step = peopleSteps.get(currentPerson) + 1;
                    if (step > maxSteps) {
                        maxSteps = step;
                    }
                    peopleSteps.put(child, step);
                }
            }
        }

        return maxSteps;
    }
}
