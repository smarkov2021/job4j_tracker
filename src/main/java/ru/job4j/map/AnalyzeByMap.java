package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int count = 0;
        int sum = 0;
        for (Pupil pup : pupils) {
            for (Subject subj : pup.subjects()) {
                sum += subj.score();
                count++;
            }
        }
        return count != 0 ? (double) sum / count : 0;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        ArrayList<Label> averageScores = new ArrayList<>();
        for (Pupil pup : pupils) {
            int count = 0;
            int sum = 0;
            for (Subject subj : pup.subjects()) {
                sum += subj.score();
                count++;
            }
            averageScores.add(new Label(pup.name(),  count != 0 ? (double) sum / count : 0));
        }
        return averageScores;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        ArrayList<Label> averageScores = new ArrayList<>();
        int count = 0;
        for (Pupil pup : pupils) {
            for (Subject subj : pup.subjects()) {
                    linkedHashMap.put(subj.name(), linkedHashMap.getOrDefault(
                            subj.name(), 0) + subj.score());
            }
            count++;
        }
        for (String name : linkedHashMap.keySet()) {
            averageScores.add(new Label(name,  count != 0 ? linkedHashMap.get(name) / count : 0));
        }
        return averageScores;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        ArrayList<Label> averageScores = new ArrayList<>();
        for (Pupil pup : pupils) {
            int count = 0;
            int sum = 0;
            for (Subject subj : pup.subjects()) {
                sum += subj.score();
                count++;
            }
            averageScores.add(new Label(pup.name(),  sum));
        }
        averageScores.sort(Comparator.naturalOrder());
        return averageScores.get(averageScores.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        ArrayList<Label> averageScores = new ArrayList<>();
        for (Pupil pup : pupils) {
            for (Subject subj : pup.subjects()) {
                    linkedHashMap.put(subj.name(), linkedHashMap.getOrDefault(
                            subj.name(), 0) + subj.score());
            }
        }
        for (String name : linkedHashMap.keySet()) {
            averageScores.add(new Label(name, linkedHashMap.get(name)));
        }
        averageScores.sort(Comparator.naturalOrder());
        return averageScores.get(averageScores.size() - 1);
    }
}