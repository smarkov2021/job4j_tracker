package ru.job4j.pojo;

import java.util.Calendar;
import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student vladimir = new Student();
        vladimir.setName("Tigranov Vladimir Nickolaevich");
        vladimir.setGroup("Applied mechanics");
        vladimir.setAdmission(new Date(121, 1, 4));
        System.out.println("Name is " + vladimir.getName());
        System.out.println("Group is " + vladimir.getGroup());
        System.out.println("Has admission " + vladimir.getAdmission());
    }
}
