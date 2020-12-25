package Exams.Microsystem;

import java.util.Objects;

public class Computer {

    private static final int DEFAULT_RAM_VALUE = 8;

    private int number;
    private Brand brand;
    private double price;
    private double screenSize;
    private String color;
    private int RAM;

    public Computer(int number, Brand brand, double price, double screenSize, String color) {
        this.number = number;
        this.RAM = DEFAULT_RAM_VALUE;
        this.brand = brand;
        this.price = price;
        this.screenSize = screenSize;
        this.color = color;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRAM() {
        return this.RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Computer computer = (Computer) o;
        return this.number == computer.number &&
                Double.compare(computer.price, this.price) == 0 &&
                Double.compare(computer.screenSize, this.screenSize) == 0 &&
                this.RAM == computer.RAM &&
                this.brand == computer.brand &&
                Objects.equals(this.color, computer.color);
    }
}
