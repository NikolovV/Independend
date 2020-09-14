package PartIII_Exercise._2_TravellingPoliceman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TravellingPoliceman {
    private static List<Street> streets = new ArrayList<>();
    private static int fuel;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        fuel = Integer.parseInt(in.readLine());
        String inputLine = in.readLine();
        streets = new ArrayList<>();

        while (!"End".equals(inputLine)) {
            String[] streetData = inputLine.split(", ");
            String streetName = streetData[0];
            int carDamage = Integer.parseInt(streetData[1]);
            int pokemonCount = Integer.parseInt(streetData[2]);
            int streetLength = Integer.parseInt(streetData[3]);
            Street street = new Street(streetName, carDamage, pokemonCount, streetLength);

            if (street.getValue() >= 0) {
                streets.add(street);
            }
            inputLine = in.readLine();
        }

        Deque<Street> visitedStreets = getVisitedStreets();
        StringBuilder sb = new StringBuilder();
        int pokemonsCaught = 0;
        int carDamage = 0;

        if (!visitedStreets.isEmpty()) {
            while (!visitedStreets.isEmpty()) {
                Street street = visitedStreets.pop();
                sb.append(street.getStreetName()).append(" -> ");
                pokemonsCaught += street.getPokemonCount();
                carDamage += street.getCarDamage();
            }
            sb.delete(sb.length() - 4, sb.length());
        }

        sb.append(System.lineSeparator())
                .append(String.format("Total pokemons caught -> %d", pokemonsCaught))
                .append(System.lineSeparator())
                .append(String.format("Total car damage -> %d", carDamage))
                .append(System.lineSeparator())
                .append(String.format("Fuel Left -> %d", fuel));
        System.out.print(sb);
    }

    private static Deque<Street> getVisitedStreets() {
        int[][] values = new int[streets.size() + 1][fuel + 1];
        boolean[][] isIncluded = new boolean[streets.size() + 1][fuel + 1];

        for (int streetIndex = 0; streetIndex < streets.size(); streetIndex++) {
            int row = streetIndex + 1;
            Street currentStreet = streets.get(streetIndex);

            for (int currentFuel = 0; currentFuel <= fuel; currentFuel++) {
                int excludedValue = values[row - 1][currentFuel];
                int includedValue = 0;

                if (currentStreet.getStreetLength() <= currentFuel) {
                    includedValue = currentStreet.getValue() + values[row - 1][currentFuel - currentStreet.getStreetLength()];
                }

                if (includedValue > excludedValue) {
                    values[row][currentFuel] = includedValue;
                    isIncluded[row][currentFuel] = true;
                } else {
                    values[row][currentFuel] = excludedValue;
                }
            }
        }

        Deque<Street> visitedStreets = new ArrayDeque<>();
        for (int index = streets.size(); index >= 1; index--) {
            if (isIncluded[index][fuel]) {
                Street street = streets.get(index - 1);
                visitedStreets.push(street);
                fuel -= street.getStreetLength();
            }
        }

        return visitedStreets;
    }
}

class Street {
    private final String streetName;
    private final int carDamage;
    private final int pokemonCount;
    private final int streetLength;

    Street(String streetName, int carDamage, int pokemonCount, int streetLength) {
        this.streetName = streetName;
        this.carDamage = carDamage;
        this.pokemonCount = pokemonCount;
        this.streetLength = streetLength;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public int getCarDamage() {
        return this.carDamage;
    }

    public int getPokemonCount() {
        return this.pokemonCount;
    }

    public int getStreetLength() {
        return this.streetLength;
    }

    public int getValue() {
        return (this.pokemonCount * 10) - this.carDamage;
    }
}
