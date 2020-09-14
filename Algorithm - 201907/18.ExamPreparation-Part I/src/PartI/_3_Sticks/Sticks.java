package PartI._3_Sticks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Sticks {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numberOfSticks = Integer.parseInt(in.readLine());
        int placings = Integer.parseInt(in.readLine());

        Map<Integer, List<Integer>> sticksUnder = new HashMap<>();
        IntStream.range(0, numberOfSticks).forEach(i -> sticksUnder.put(i, new ArrayList<>()));

        int[] sticksOnTop = new int[numberOfSticks];
        for (int i = 0; i < placings; i++) {
            String[] tokens = in.readLine().split(" ");
            int upperStick = Integer.parseInt(tokens[0]);
            int downStick = Integer.parseInt(tokens[1]);
            sticksOnTop[downStick]++;
            sticksUnder.get(upperStick).add(downStick);
        }

        StringBuilder sb = new StringBuilder();
        int removedSticksCount = 0;
        while (removedSticksCount < numberOfSticks) {
            int stick = -1;
            for (int i = 0; i < numberOfSticks; i++) {
                if (sticksOnTop[i] == 0 && i > stick) {
                    stick = i;
                }
            }
            if (stick == -1) {
                System.out.println("Cannot lift all sticks");
                break;
            }
            sticksOnTop[stick] = -1;
            sb.append(stick).append(" ");
            for (Integer child : sticksUnder.get(stick)) {
                sticksOnTop[child]--;
            }
            removedSticksCount++;
        }

        System.out.println(sb);
    }
}