package ru.job4j.travel;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " - Движение осуществляется по скоростным трассам");
    }

    @Override
    public void engine() {
        System.out.println("Используется ДВС");
    }
}
