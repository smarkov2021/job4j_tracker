package ru.job4j.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMap(i -> i.subjects().stream())
                .mapToInt(Subject::score)
                .average()
                .orElse(0);
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.
                map(i -> new Tuple(i.name(), i.subjects()
                        .stream()
                        .mapToInt(Subject::score)
                        .average()
                        .orElse(0)))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        Map<String, Double> var = stream.flatMap(i -> i.subjects().stream())
                .collect(Collectors.groupingBy(Subject::name,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::score)));
        return var
                .entrySet()
                .stream()
                .map(i -> new Tuple(i.getKey(), i.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.
                map(i -> new Tuple(i.name(), i.subjects()
                        .stream()
                        .mapToInt(Subject::score)
                        .sum()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        Map<String, Double> var = stream.flatMap(i -> i.subjects().stream())
                .collect(Collectors.groupingBy(Subject::name,
                        Collectors.summingDouble(Subject::score)));
        return var
                .entrySet()
                .stream().map(i -> new Tuple(i.getKey(), i.getValue()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }
}