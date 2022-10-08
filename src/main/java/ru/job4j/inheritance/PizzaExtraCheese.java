package ru.job4j.inheritance;

public class PizzaExtraCheese extends Pizza {
    public PizzaExtraCheese() {
        super();
    }

    @Override
    public String name() {
        return super.name() + " + extra cheese";
    }
}
