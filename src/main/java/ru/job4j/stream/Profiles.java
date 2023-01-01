package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile :: getAddress)
                .collect(Collectors.toList());
    }

    public static List<Address> collectSortWithoutDuplicate(List<Profile> profiles) {
        Comparator<Address> comparator = (left, right) -> {
           return left.getCity().compareTo(right.getCity());
        };
        return profiles.stream()
                .map(Profile :: getAddress)
                .sorted(Comparator.comparing(obj -> obj.getCity()))
                .distinct()
                .collect(Collectors.toList());
    }

}
