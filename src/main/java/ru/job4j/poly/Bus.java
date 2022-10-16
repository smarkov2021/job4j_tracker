package ru.job4j.poly;

public class Bus implements Transport {
    private double price = 50;

    @Override
    public void drive() {
        System.out.println("Поездка началась");
    }

    @Override
    public void passengers(int count) {
        System.out.println(" Количество пассажиров " + count);
    }

    @Override
    public double refuel(double value) {
        return value * price;
    }
}
