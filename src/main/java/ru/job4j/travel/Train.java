package ru.job4j.travel;

public class Train implements Vehicle {

    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " - Движение осуществляется по рельсам");
    }

    @Override
    public void engine() {
        System.out.println("Используется электродвигатель");
    }
}
