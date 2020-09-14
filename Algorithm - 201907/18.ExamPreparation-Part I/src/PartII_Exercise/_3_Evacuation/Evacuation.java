package PartII_Exercise._3_Evacuation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Evacuation {
    private static List<Integer>[] corridors;
    private static List<Integer>[] corridorTimes;
    private static int[] exitRooms;
    private static int[] bestTimes;
    private static int maxTimeToExit;

    public static void main(String[] args) throws IOException {
        readInput();
        modifiedDijkstra();
        List<Integer> unsafe = new ArrayList<>();
        int last = 0;
        for (int i = 0; i < bestTimes.length; i++) {
            int time = bestTimes[i];
            if (time > bestTimes[last]) {
                last = i;
            }
            if (time > maxTimeToExit) {
                unsafe.add(i);
            }
        }
        System.out.println(buildOutput(unsafe, last));
    }

    private static void readInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int roomsCount = Integer.parseInt(in.readLine());
        String[] tokens = in.readLine().split(" ");
        exitRooms = new int[tokens.length];
        bestTimes = new int[roomsCount];

        for (int i = 0; i < roomsCount; i++) {
            bestTimes[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < tokens.length; i++) {
            exitRooms[i] = Integer.parseInt(tokens[i]);
        }

        int corridorCount = Integer.parseInt(in.readLine());
        corridors = new ArrayList[roomsCount];
        corridorTimes = new ArrayList[roomsCount];
        for (int i = 0; i < corridors.length; i++) {
            corridors[i] = new ArrayList<>();
            corridorTimes[i] = new ArrayList<>();
        }

        for (int i = 0; i < corridorCount; i++) {
            tokens = in.readLine().split(" ");
            int room1 = Integer.parseInt(tokens[0]);
            int room2 = Integer.parseInt(tokens[1]);
            int minutes = Integer.parseInt(tokens[2].substring(0, 2));
            int seconds = Integer.parseInt(tokens[2].substring(3));
            int totalSeconds = minutes * 60 + seconds;
            corridors[room1].add(room2);
            corridors[room2].add(room1);
            corridorTimes[room2].add(totalSeconds);
            corridorTimes[room1].add(totalSeconds);

        }

        tokens = in.readLine().split(":");
        int minutes = Integer.parseInt(tokens[0]);
        int seconds = Integer.parseInt(tokens[1]);
        maxTimeToExit = minutes * 60 + seconds;
    }

    private static void modifiedDijkstra() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> bestTimes[i]));
        for (int room : exitRooms) {
            bestTimes[room] = 0;
            queue.offer(room);
        }

        while (!queue.isEmpty()) {
            int room = queue.poll();
            for (int i = 0; i < corridors[room].size(); i++) {
                int child = corridors[room].get(i);
                int timeToChild = corridorTimes[room].get(i);
                int totalTime = bestTimes[room] + timeToChild;
                if (bestTimes[child] > totalTime) {
                    bestTimes[child] = totalTime;
                    if (!queue.contains(child)) {
                        queue.offer(child);
                    }
                }
            }
        }
    }

    private static String buildOutput(List<Integer> unsafe, int last) {
        StringBuilder sb = new StringBuilder();
        if (unsafe.isEmpty()) {
            int seconds = bestTimes[last];
            int hours = seconds / 3600;
            seconds = seconds % 3600;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            sb.append("Safe\n");
            sb.append(last).append(" (");
            appendLeadingZero(sb, hours).append(":");
            appendLeadingZero(sb, minutes).append(":");
            appendLeadingZero(sb, seconds).append(")");
        } else {
            sb.append("Unsafe\n");
            for (Integer room : unsafe) {
                int seconds = bestTimes[room];
                int hours = seconds / 3600;
                seconds = seconds % 3600;
                int minutes = seconds / 60;
                seconds = seconds % 60;
                if (bestTimes[room] < Integer.MAX_VALUE) {
                    sb.append(room).append(" (");
                    appendLeadingZero(sb, hours).append(":");
                    appendLeadingZero(sb, minutes).append(":");
                    appendLeadingZero(sb, seconds).append(")");
                } else {
                    sb.append(room).append(" (unreachable)");
                }
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    private static StringBuilder appendLeadingZero(StringBuilder sb, int hours) {
        if (hours == 0) {
            sb.append("00");
        } else if (hours < 10) {
            sb.append("0").append(hours);
        } else {
            sb.append(hours);
        }
        return sb;
    }

}
