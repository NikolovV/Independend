package Part_II.Lab._2_Guitar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Guitar {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] volumes = Arrays.stream(in.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int initialVolume = Integer.parseInt(in.readLine());
        int maxVolume = Integer.parseInt(in.readLine());

        System.out.println(findMaxVolume(volumes, initialVolume, maxVolume));
    }

    private static int findMaxVolume(int[] volumes, int initialVolume, int maxVolume) {
        boolean[][] maxVolumeMatrix = new boolean[volumes.length + 1][maxVolume + 1];
        maxVolumeMatrix[0][initialVolume] = true;

        for (int volumeElement = 1; volumeElement <= volumes.length; volumeElement++) {
            int currentVolumeChange = volumes[volumeElement - 1];
            boolean isExceedVolume = true;

            for (int volumeLevel = 0; volumeLevel <= maxVolume; volumeLevel++) {
                if (!maxVolumeMatrix[volumeElement - 1][volumeLevel]) {
                    continue;
                }
                isExceedVolume = false;
                int increasedVolume = volumeLevel + currentVolumeChange;
                int decreasedVolume = volumeLevel - currentVolumeChange;

                if (increasedVolume <= maxVolume) {
                    maxVolumeMatrix[volumeElement][increasedVolume] = true;
                }

                if (decreasedVolume >= 0) {
                    maxVolumeMatrix[volumeElement][decreasedVolume] = true;
                }
            }

            if (isExceedVolume) {
                return -1;
            }
        }

        int maxPossibleVolume = -1;
        for (int i = maxVolumeMatrix[maxVolumeMatrix.length - 1].length - 1; i >= 0; i--) {
            if (maxVolumeMatrix[maxVolumeMatrix.length - 1][i]) {
                maxPossibleVolume = i;
                break;
            }
        }

        return maxPossibleVolume;
    }
}
