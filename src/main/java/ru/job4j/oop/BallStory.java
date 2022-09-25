package ru.job4j.oop;

public class BallStory {
    public static void main(String[] args) {
        Fox alice = new Fox();
        Wolf sharik = new Wolf();
        Ball kolobok = new Ball();
        Hare ushasty = new Hare();
        ushasty.tryEat(kolobok);
        sharik.tryEat(kolobok);
        alice.tryEat(kolobok);
    }
}
