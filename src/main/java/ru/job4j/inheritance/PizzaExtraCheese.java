package ru.job4j.inheritance;

public class PizzaExtraCheese extends Pizza {
    public PizzaExtraCheese() {
        super();
    }

    @Override
    public String name() {
        Pizza hutt = new Pizza();
        return hutt.name() + " + extra cheese";
    }
}
