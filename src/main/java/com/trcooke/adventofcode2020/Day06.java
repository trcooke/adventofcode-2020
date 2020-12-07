package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay06");
        Set<Character> yesQuestions = new HashSet<>();
        int sumOfYesQuestions = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isEmpty()) {
                sumOfYesQuestions += yesQuestions.size();
                yesQuestions.clear();
            } else {
                for (char c : line.toCharArray()) {
                    yesQuestions.add(c);
                }
            }
        }
        sumOfYesQuestions += yesQuestions.size();
        return sumOfYesQuestions;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay06");
        int sumOfYesQuestions = 0;
        List<Set<Character>> personYesQuestions = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isEmpty()) {
                Set<Character> matchingYesQuestions = new HashSet<>();
                boolean firstEntry = true;
                for (Set<Character> personYesQuestion : personYesQuestions) {
                    if (firstEntry) {
                        matchingYesQuestions.addAll(personYesQuestion);
                        firstEntry = false;
                    } else {
                        matchingYesQuestions.retainAll(personYesQuestion);
                    }
                }
                sumOfYesQuestions += matchingYesQuestions.size();
                personYesQuestions.clear();
            } else {
                Set<Character> thisPersonYesQuestions = new HashSet<>();
                for (char c : line.toCharArray()) {
                    thisPersonYesQuestions.add(c);
                }
                personYesQuestions.add(thisPersonYesQuestions);
            }
        }
        Set<Character> matchingYesQuestions = new HashSet<>();
        boolean firstEntry = true;
        for (Set<Character> personYesQuestion : personYesQuestions) {
            if (firstEntry) {
                matchingYesQuestions.addAll(personYesQuestion);
                firstEntry = false;
            } else {
                matchingYesQuestions.retainAll(personYesQuestion);
            }
        }
        sumOfYesQuestions += matchingYesQuestions.size();
        return sumOfYesQuestions;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day06 solution = new Day06();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
