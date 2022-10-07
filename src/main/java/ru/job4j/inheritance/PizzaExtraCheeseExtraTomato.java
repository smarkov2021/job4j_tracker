package ru.job4j.inheritance;

public class PizzaExtraCheeseExtraTomato extends PizzaExtraCheese {
    public PizzaExtraCheeseExtraTomato() {
        super();
    }

    @Override
    public String name() {
        PizzaExtraCheese hutt = new PizzaExtraCheese();
        return hutt.name() + " + extra tomato";
    }
}
