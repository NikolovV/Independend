package PartI._4_Robbery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Robbery {
    private static Long[][] mapCityGraph;
    private static boolean[] isCameraOn;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        mapCityGraph = new Long[input.length][input.length];
        isCameraOn = new boolean[input.length];
        for (int i = 0; i < input.length; i++) {
            isCameraOn[i] = input[i].charAt(input[i].length() - 1) == 'w';
        }
        long initialEnergy = Long.parseLong(in.readLine());
        int waitOneTurnCost = Integer.parseInt(in.readLine());
        int startPoint = Integer.parseInt(in.readLine());
        int endPoint = Integer.parseInt(in.readLine());

        int connectionsCnt = Integer.parseInt(in.readLine());
        for (int i = 0; i < connectionsCnt; i++) {
            input = in.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            long cost = Long.parseLong(input[2]);
            mapCityGraph[from][to] = cost;
        }

        long requiredEnergy = dijkstra(startPoint, endPoint, waitOneTurnCost);

        if (initialEnergy >= requiredEnergy) {
            System.out.println(initialEnergy - requiredEnergy);
        } else {
            System.out.printf("Busted - need %d more energy%n", requiredEnergy - initialEnergy);
        }
    }

    private static long dijkstra(int start, int destination, int waitCost) {
        long[] cost = new long[mapCityGraph.length];
        boolean[] isOdd = new boolean[mapCityGraph.length];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Long.MAX_VALUE;
        }

        cost[start] = 0L;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> cost[i]));
        queue.offer(start);
        isOdd[start] = true;
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int i = 0; i < mapCityGraph.length; i++) {
                Long costToChild = mapCityGraph[currentNode][i];
                if (costToChild != null) {
                    boolean shouldWait = (isOdd[currentNode] && !isCameraOn[i]) || (!isOdd[currentNode] && isCameraOn[i]);
                    long totalCost = cost[currentNode] + costToChild;
                    if (shouldWait) {
                        totalCost += waitCost;
                    }
                    if (cost[i] > totalCost) {
                        isOdd[i] = shouldWait == isOdd[currentNode];
                        cost[i] = totalCost;
                        queue.offer(i);
                    }
                }
            }
        }
        return cost[destination];
    }
}
