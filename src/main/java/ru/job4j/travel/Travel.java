package ru.job4j.travel;

public class Travel {
    public static void main(String[] args) {
        Vehicle higer = new Bus();
        Vehicle lastochka = new Train();
        Vehicle boeing = new Airplane();
        Vehicle[] vehicles = new Vehicle[] {higer, lastochka, boeing};
        for (Vehicle vec: vehicles) {
            vec.move();
            vec.engine();
        }
    }
}
