package Part_II.Exercise._2_FastAndFurious;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FastAndFurious {
    private static double[][] graph;
    private static final Map<String, List<CameraRecord>> carRecords = new HashMap<>();
    private static final Map<String, Integer> townIndices = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        int locationIndex = 0;

        List<Road> roadList = new ArrayList<>();
        while (!"Records:".equals(input = in.readLine())) {
            String[] roadsData = input.split(" ");
            String firstTown = roadsData[0];
            String secondTown = roadsData[1];
            double distance = Double.parseDouble(roadsData[2]);
            double speedLimit = Double.parseDouble(roadsData[3]);

            Road road = new Road(firstTown, secondTown, ((distance / speedLimit) * 3600));
            roadList.add(road);

            if (!townIndices.containsKey(firstTown)) {
                townIndices.put(firstTown, locationIndex++);
            }
            if (!townIndices.containsKey(secondTown)) {
                townIndices.put(secondTown, locationIndex++);
            }
        }

        graph = new double[townIndices.size()][townIndices.size()];
        for (Road road : roadList) {
            String firstLocation = road.first;
            String secondLocation = road.second;
            int firstIndex = townIndices.get(firstLocation);
            int secondIndex = townIndices.get(secondLocation);
            graph[firstIndex][secondIndex] = road.minTime;
            graph[secondIndex][firstIndex] = road.minTime;
        }

        Set<String> speeders = new HashSet<>();
        while (!"End".equals(input = in.readLine())) {
            String[] recordsData = input.split(" ");
            String licencePlate = recordsData[1];

            if (speeders.contains(licencePlate)) {
                continue;
            }

            String location = recordsData[0];
            String time = recordsData[2];

            int destinationIndex = townIndices.get(location);
            CameraRecord currentCameraRecord = new CameraRecord(destinationIndex, time);

            if (carRecords.containsKey(licencePlate)) {
                for (CameraRecord record : carRecords.get(licencePlate)) {
                    if (isCarSpeeding(destinationIndex, currentCameraRecord.timeInSeconds, record)) {
                        speeders.add(licencePlate);
                        break;
                    }
                }
            }
            carRecords.putIfAbsent(licencePlate, new ArrayList<>());
            carRecords.get(licencePlate).add(currentCameraRecord);
        }

        StringBuilder sb = new StringBuilder();
        speeders.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(licensePlate -> sb.append(licensePlate).append(System.lineSeparator()));
        System.out.print(sb);
    }

    private static boolean isCarSpeeding(int destinationIndex, int totalSeconds, CameraRecord record) {
        int previousSeconds = record.timeInSeconds;
        int timeTravelled = Math.abs(totalSeconds - previousSeconds);
        int startIndex = record.townIndex;
        double timeAllowed = dijkstra(startIndex, destinationIndex);

        return timeTravelled < timeAllowed;
    }

    private static double dijkstra(int startIndex, int destinationIndex) {
        double[] time = new double[graph.length];
        for (int i = 0; i < time.length; i++) {
            time[i] = Double.MAX_VALUE;
        }
        time[startIndex] = 0D;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> time[i]));
        queue.offer(startIndex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int i = 0; i < graph.length; i++) {
                double distanceToChild = graph[vertex][i];
                double totalDistance = time[vertex] + distanceToChild;

                if (distanceToChild != 0D && time[i] > totalDistance) {
                    time[i] = totalDistance;
                    queue.remove(i);
                    queue.offer(i);
                }
            }
        }

        return time[destinationIndex] != Double.MAX_VALUE ? time[destinationIndex] : 0D;
    }
}

class Road {
    public String first;
    public String second;
    public double minTime;

    public Road(String first, String second, double minTime) {
        this.first = first;
        this.second = second;
        this.minTime = minTime;
    }
}

class CameraRecord {
    public int townIndex;
    public int timeInSeconds;

    public CameraRecord(int townIndex, String time) {
        this.townIndex = townIndex;
        this.timeInSeconds = this.convertTimeToSecond(time);
    }

    private int convertTimeToSecond(String time) {
        String[] timeData = time.split(":");
        int hours = Integer.parseInt(timeData[0]);
        int minutes = Integer.parseInt(timeData[1]);
        int seconds = Integer.parseInt(timeData[2]);

        return hours * 3600 + minutes * 60 + seconds;
    }
}