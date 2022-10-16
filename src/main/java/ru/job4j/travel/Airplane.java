package ru.job4j.travel;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " - Движение осуществляется по воздуху");
    }

    @Override
    public void engine() {
        System.out.println("Используется турбореактивный двигатель");
    }
}
