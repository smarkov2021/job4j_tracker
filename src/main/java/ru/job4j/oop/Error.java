package ru.job4j.oop;

public class Error {

    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активна: " + active);
        System.out.println("Статус: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error firstError = new Error();
        Error secondError = new Error(true, 1, "Деление на ноль");
        Error thirdError = new Error(false, 2, "Выход за рамки диапазона");
        firstError.printInfo();
        secondError.printInfo();
        thirdError.printInfo();
    }
}
