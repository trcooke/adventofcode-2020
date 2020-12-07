package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day07 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay07");
        Map<String, Set<String>> bags = new HashMap<>();
        for (String line; (line = reader.readLine()) != null;) {
            String bagName = line.split(" bags contain ")[0];
            Set<String> contains = new HashSet<>();
            if (!line.split(" bags contain ")[1].equals("no other bags.")) {
                String[] containsStrings = line.split(" bags contain ")[1].split(", ");
                for (String containsString : containsStrings) {
                    String containsBagName = containsString.split(" ")[1] + " " + containsString.split(" ")[2];
                    contains.add(containsBagName.trim());
                }
            }
            bags.put(bagName.trim(), contains);
        }
        Set<String> bagsContainingShinyGoldBag = new HashSet<>();
        for (String bag : bags.keySet()) {
            if (containsBag(bag, bags, "shiny gold")) {
                bagsContainingShinyGoldBag.add(bag);
            }
        }
        return bagsContainingShinyGoldBag.size();
    }

    private boolean containsBag(String bag, Map<String, Set<String>> bags, String shinyGold) {
        if (bags.get(bag).contains(shinyGold)) {
            return true;
        }
        for (String subBag : bags.get(bag)) {
            if (containsBag(subBag, bags, shinyGold)) {
                return true;
            }
        }
        return false;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay07");
        Map<String, List<String>> bags = new HashMap<>();
        for (String line; (line = reader.readLine()) != null;) {
            String bagName = line.split(" bags contain ")[0];
            List<String> contains = new ArrayList<>();
            if (!line.split(" bags contain ")[1].equals("no other bags.")) {
                String[] containsStrings = line.split(" bags contain ")[1].split(", ");
                for (String containsString : containsStrings) {
                    String containsBagName = containsString.split(" ")[1] + " " + containsString.split(" ")[2];
                    int count = Integer.parseInt(containsString.split(" ")[0]);
                    for (int i = 0; i < count; i++) {
                        contains.add(containsBagName.trim());
                    }
                }
            }
            bags.put(bagName.trim(), contains);
        }
        List<String> shinyGoldBagContent = bags.get("shiny gold");
        return countContent(shinyGoldBagContent, bags);
    }

    private int countContent(List<String> content, Map<String, List<String>> bags) {
        if (content.size() == 0) {
            return 0;
        }
        int contentCount = content.size();
        for (String subBag : content) {
            contentCount += countContent(bags.get(subBag), bags);
        }
        return contentCount;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day07 solution = new Day07();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Bag {
        String name;
        Set<String> containedBy = new HashSet<>();
        Collection<String> contains = new ArrayList<>();
    }
}
