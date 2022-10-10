package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book adventure = new Book("Adventure", 118);
        Book fall = new Book("Fall of Bastile", 323);
        Book wolf = new Book("Lonely wolf", 459);
        Book cleancode = new Book("Clean Code", 1495);

        Book[] books = new Book[4];
        books[0] = adventure;
        books[1] = wolf;
        books[2] = fall;
        books[3] = cleancode;

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPagesCount());
        }

        Book first = books[0];
        books[0] = books[3];
        books[3] = first;

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPagesCount());
        }

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            if (("Clean Code".equals(bk.getName()))) {
                System.out.println(bk.getName() + " - " + bk.getPagesCount());
            }
        }
    }
}
