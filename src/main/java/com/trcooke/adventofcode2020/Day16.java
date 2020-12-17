package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Day16 {

    Integer part1() throws IOException {
        BufferedReader reader = getInput("InputDay16");
        List<Rule> rules = new ArrayList<>();
        List<Integer> yourTicketValues = new ArrayList<>();
        List<List<Integer>> nearbyTickets = new ArrayList<>();
        int inputSection = 1;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isEmpty()) {
                inputSection++;
                continue;
            }
            if (inputSection == 1) {
                String[] ranges = line.split(": ")[1].split(" or ");
                Rule rule = new Rule(
                        line.split(": ")[0],
                        new Range(Integer.parseInt(ranges[0].split("-")[0]), Integer.parseInt(ranges[0].split("-")[1])),
                        new Range(Integer.parseInt(ranges[1].split("-")[0]), Integer.parseInt(ranges[1].split("-")[1])));
                rules.add(rule);
            } else if (inputSection == 2) {
                if (line.startsWith("your ticket")) {
                    continue;
                }
                for (String val : line.split(",")) {
                    yourTicketValues.add(Integer.valueOf(val));
                }
            } else {
                if (line.startsWith("nearby tickets")) {
                    continue;
                }
                List<Integer> nearbyTicketVals = new ArrayList<>();
                for (String val : line.split(",")) {
                    nearbyTicketVals.add(Integer.valueOf(val));
                }
                nearbyTickets.add(nearbyTicketVals);
            }
        }
        List<Integer> invalidValues = new ArrayList<>();
        for (List<Integer> nearbyTicket : nearbyTickets) {
            for (Integer ticketValue : nearbyTicket) {
                boolean valid = false;
                for (Rule rule : rules) {
                    if (rule.validate(ticketValue)) {
                        valid = true;
                    }
                }
                if (!valid) {
                    invalidValues.add(ticketValue);
                }
            }
        }
        return invalidValues.stream().reduce(Integer::sum).get();
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("InputDay16");
        List<Rule> rules = new ArrayList<>();
        List<Integer> yourTicketValues = new ArrayList<>();
        List<List<Integer>> nearbyTickets = new ArrayList<>();
        int inputSection = 1;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isEmpty()) {
                inputSection++;
                continue;
            }
            if (inputSection == 1) {
                String[] ranges = line.split(": ")[1].split(" or ");
                Rule rule = new Rule(
                        line.split(": ")[0],
                        new Range(Integer.parseInt(ranges[0].split("-")[0]), Integer.parseInt(ranges[0].split("-")[1])),
                        new Range(Integer.parseInt(ranges[1].split("-")[0]), Integer.parseInt(ranges[1].split("-")[1])));
                rules.add(rule);
            } else if (inputSection == 2) {
                if (line.startsWith("your ticket")) {
                    continue;
                }
                for (String val : line.split(",")) {
                    yourTicketValues.add(Integer.valueOf(val));
                }
            } else {
                if (line.startsWith("nearby tickets")) {
                    continue;
                }
                List<Integer> nearbyTicketVals = new ArrayList<>();
                for (String val : line.split(",")) {
                    nearbyTicketVals.add(Integer.valueOf(val));
                }
                nearbyTickets.add(nearbyTicketVals);
            }
        }

        List<List<Integer>> validNearbyTickets = new ArrayList<>();
        for (List<Integer> nearbyTicket : nearbyTickets) {
            boolean ticketValid = true;
            for (Integer ticketValue : nearbyTicket) {
                boolean valid = false;
                for (Rule rule : rules) {
                    if (rule.validate(ticketValue)) {
                        valid = true;
                    }
                }
                ticketValid = ticketValid && valid;
            }
            if (ticketValid) {
                validNearbyTickets.add(nearbyTicket);
            }
        }

        Map<Integer, Set<String>> fieldsByIndex = new HashMap<>();
        for (List<Integer> nearbyTicket : validNearbyTickets) {
            for (int i = 0; i < nearbyTicket.size(); i++) {
                Integer ticketValue = nearbyTicket.get(i);
                Set<String> existingRules = fieldsByIndex.getOrDefault(i, rules.stream().map(r -> r.name).collect(Collectors.toSet()));
                Set<String> theseRules = new HashSet<>();
                for (Rule rule : rules) {
                    if (rule.validate(ticketValue)) {
                        theseRules.add(rule.name);
                    }
                }
                if (theseRules.size() == 1) {
                    fieldsByIndex.put(i, existingRules);
                    continue;
                }
                existingRules.retainAll(theseRules);
                fieldsByIndex.put(i, existingRules);
            }
        }
        Map<Integer, String> finalFields = new HashMap<>();
        do {
            for (Integer i : fieldsByIndex.keySet()) {
                if (fieldsByIndex.get(i).size() == 1) {
                    String val = fieldsByIndex.get(i).stream().findFirst().get();
                    finalFields.put(i, val);
                    fieldsByIndex.remove(i);
                    for (Integer index : fieldsByIndex.keySet()) {
                        fieldsByIndex.get(index).remove(val);
                    }
                    break;
                }
            }
        } while (!fieldsByIndex.isEmpty());
        System.out.println(finalFields);
        long output = 1;
        for (Integer fieldIndex : finalFields.keySet()) {
            if (finalFields.get(fieldIndex).startsWith("departure")) {
                System.out.println(yourTicketValues.get(fieldIndex));
                output *= yourTicketValues.get(fieldIndex);
            }
        }

        return output;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day16 solution = new Day16();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Rule {
        private String name;
        private Range[] ranges;

        public Rule(String name, Range... ranges) {
            this.name = name;
            this.ranges = ranges;
        }

        @Override
        public String toString() {
            return "Rule{" +
                    "name='" + name + '\'' +
                    ", ranges=" + Arrays.toString(ranges) +
                    '}';
        }

        public boolean validate(Integer ticketValue) {
            for (Range range : ranges) {
                if (ticketValue >= range.lowerBound && ticketValue <= range.upperBound) {
                    return true;
                }
            }
            return false;
        }
    }

    private class Range {
        private final Integer lowerBound;
        private final Integer upperBound;

        public Range(Integer lowerBound, Integer upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "lowerBound=" + lowerBound +
                    ", upperBound=" + upperBound +
                    '}';
        }
    }
}
