package ru.job4j.oop;

public class Cat {

    private String food;
    private String name;

    void giveNick(String nick) {
      this.name = nick;
    }

    public void show() {
        System.out.println("There are " + this.name + "'s " + this.food);
    }

    public void eat(String meat) {
        this.food = meat;
    }
}