package Exams.Microsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MicrosystemImpl implements Microsystem {
    private final Map<Integer, Computer> computerByNumber;

    public MicrosystemImpl() {
        this.computerByNumber = new HashMap<>();
    }

    @Override
    public void createComputer(Computer computer) {
        if (this.computerByNumber.containsKey(computer.getNumber())) {
            throw new IllegalArgumentException();
        }
        this.computerByNumber.putIfAbsent(computer.getNumber(), computer);
    }

    @Override
    public boolean contains(int number) {
        return this.computerByNumber.containsKey(number);
    }

    @Override
    public int count() {
        return this.computerByNumber.size();
    }

    @Override
    public Computer getComputer(int number) {
        if (!this.computerByNumber.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        return this.computerByNumber.get(number);
    }

    @Override
    public void remove(int number) {
        if (!this.computerByNumber.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        this.computerByNumber.remove(number);
    }

    @Override
    public void removeWithBrand(Brand brand) {
        boolean removed = this.computerByNumber
                .values()
                .removeIf(computer -> computer.getBrand().equals(brand));

        if (!removed) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void upgradeRam(int ram, int number) {
        if (!this.computerByNumber.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        Computer currentComputer = this.computerByNumber.get(number);
        if (currentComputer.getRAM() < ram) {
            currentComputer.setRAM(ram);
        }
    }

    @Override
    public Iterable<Computer> getAllFromBrand(Brand brand) {
        return this.computerByNumber.values().stream()
                .filter(computer -> computer.getBrand().equals(brand))
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getAllWithScreenSize(double screenSize) {
        return this.computerByNumber.values().stream()
                .filter(computer -> computer.getScreenSize() == screenSize)
                .sorted((a, b) -> b.getNumber() - a.getNumber())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Iterable<Computer> getAllWithColor(String color) {
        return this.computerByNumber.values().stream()
                .filter(computer -> computer.getColor().equals(color))
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getInRangePrice(double minPrice, double maxPrice) {
        return this.computerByNumber.values().stream()
                .filter(computer -> computer.getPrice() >= minPrice && computer.getPrice() <= maxPrice)
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }
}
