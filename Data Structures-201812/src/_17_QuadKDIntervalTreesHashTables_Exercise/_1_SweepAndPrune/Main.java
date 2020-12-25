package _17_QuadKDIntervalTreesHashTables_Exercise._1_SweepAndPrune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        GameArea gameArea = new GameArea();

        String input = reader.readLine();
        while (!"end".equals(input)) {
            CommandParser.executeCommand(gameArea, input);
            input = reader.readLine();
        }

        gameArea.showLog();
    }
}
