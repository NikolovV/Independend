package Part_I.Exercise._1_ParkingZones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingZones {
    private static List<FreeParkingSpace> freeParkingSpaceList;
    private static int targetX;
    private static int targetY;
    private static int timeToTraverse;
    private static int minMoves;

    public static void main(String[] args) {
        readInput();
        FreeParkingSpace bestFreeParkingSpace = findBestParkSpace();
        printBestParkingZone(bestFreeParkingSpace);
    }

    private static void readInput() {
        Scanner in = new Scanner(System.in);
        int parkingZoneCount = Integer.parseInt(in.nextLine());

        List<ParkingZone> parkingZoneList = new ArrayList<>();
        for (int i = 0; i < parkingZoneCount; i++) {
            String[] line = in.nextLine().split(":");
            String zoneName = line[0];
            String[] info = line[1].trim().split(", ");
            int x = Integer.parseInt(info[0]);
            int y = Integer.parseInt(info[1]);
            int width = x + Integer.parseInt(info[2]) - 1;
            int height = y + Integer.parseInt(info[3]) - 1;
            double price = Double.parseDouble(info[4]);

            parkingZoneList.add(new ParkingZone(zoneName, x, y, width, height, price));
        }

        freeParkingSpaceList = new ArrayList<>();
        String[] freeParkingZones = in.nextLine().split("; ");
        for (String freeParkingZone : freeParkingZones) {
            String[] freeZoneCoordinate = freeParkingZone.split(", ");
            int x = Integer.parseInt(freeZoneCoordinate[0]);
            int y = Integer.parseInt(freeZoneCoordinate[1]);

            FreeParkingSpace freeParkingSpace = new FreeParkingSpace(x, y);
            for (ParkingZone parkingZone : parkingZoneList) {
                if (parkingZone.isInZone(x, y)) {
                    freeParkingSpace.setParkingZone(parkingZone);
                    break;
                }
            }
            freeParkingSpaceList.add(freeParkingSpace);
        }

        String[] targetPointData = in.nextLine().split(", ");
        targetX = Integer.parseInt(targetPointData[0]);
        targetY = Integer.parseInt(targetPointData[1]);
        timeToTraverse = Integer.parseInt(in.nextLine());
    }

    private static FreeParkingSpace findBestParkSpace() {
        minMoves = Integer.MAX_VALUE;
        FreeParkingSpace bestParkingSpace = freeParkingSpaceList.get(0);

        for (FreeParkingSpace freeParkingSpace : freeParkingSpaceList) {
            int totalDistance = -1;

            totalDistance += Math.abs(freeParkingSpace.getY() - targetY);
            totalDistance += Math.abs(targetX - freeParkingSpace.getX());

            if (totalDistance <= minMoves &&
                    freeParkingSpace.getParkingZone().getPricePerMinute() <= bestParkingSpace.getParkingZone().getPricePerMinute()) {
                minMoves = totalDistance;
                bestParkingSpace = freeParkingSpace;
            }
        }

        return bestParkingSpace;
    }

    private static void printBestParkingZone(FreeParkingSpace bestFreeParkingSpace) {
        double totalTime = Math.ceil((minMoves * 2 * timeToTraverse) / 60.0);
        double cost = totalTime * bestFreeParkingSpace.getParkingZone().getPricePerMinute();

        System.out.printf("%s Price: %.2f", bestFreeParkingSpace.toString(), cost);
    }

    private static class ParkingZone {
        private final String name;
        private final int x;
        private final int y;
        private final int width;
        private final int height;
        private final double pricePerMinute;

        ParkingZone(String name, int x, int y, int width, int height, double pricePerMinute) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.pricePerMinute = pricePerMinute;
        }

        public String getName() {
            return this.name;
        }

        public double getPricePerMinute() {
            return this.pricePerMinute;
        }

        public boolean isInZone(int x, int y) {
            return (x >= this.x && x <= this.width &&
                    y >= this.y && y <= this.height);
        }
    }

    private static class FreeParkingSpace {
        private final int x;
        private final int y;
        private ParkingZone parkingZone;

        FreeParkingSpace(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setParkingZone(ParkingZone parkingZone) {
            this.parkingZone = parkingZone;
        }

        public ParkingZone getParkingZone() {
            return this.parkingZone;
        }

        @Override
        public String toString() {
            return String.format("Zone Type: %s; X: %d; Y: %d;",
                    this.parkingZone.getName(),
                    this.x,
                    this.y);
        }
    }
}
