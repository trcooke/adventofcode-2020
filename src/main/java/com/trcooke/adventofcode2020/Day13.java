package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay13");
        List<String> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(line);
        }
        int timestamp = Integer.parseInt(input.get(0));
        List<Integer> busses = new ArrayList<>();
        for (String bus : input.get(1).split(",")) {
            if (!"x".equals(bus)) {
                busses.add(Integer.valueOf(bus));
            }
        }
        int nextBusWait = timestamp;
        int nextBus = 0;
        for (Integer bus : busses) {
            int nextDepart = ((timestamp / bus) * bus) + bus;
            if (nextDepart - timestamp <= nextBusWait) {
                nextBusWait = nextDepart - timestamp;
                nextBus = bus;
            }
        }
        return nextBusWait * nextBus;
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("InputDay13Test");
        List<String> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(line);
        }
        String[] busses = input.get(1).split(",");
        return findStartTime(busses, 100000000000000L);
    }

    long findStartTime(String[] busses, long fromTime) {
        boolean solutionFound;
        long highestBusNum = 0;
        int highestBusNumIndex = 0;
        for (int i = 0; i < busses.length; i++) {
            String bus = busses[i];
            if (!"x".equals(bus)) {
                long busNum = Long.parseLong(bus);
                if (busNum >= highestBusNum) {
                    highestBusNum = busNum;
                    highestBusNumIndex = i;
                }
            }
        }
        long time = ((fromTime / highestBusNum) * highestBusNum) + highestBusNum;
        do {
            solutionFound = true;
            for (int offset = 0; offset < busses.length; offset++) {
                if (!departsAt(busses[offset], time - highestBusNumIndex + offset)) {
                    solutionFound = false;
                }
            }
            if (!solutionFound) {
                time += highestBusNum * Long.parseLong(busses[0]);
            }
        } while (!solutionFound);
        return time - highestBusNumIndex;
    }

    private boolean departsAt(String bus, long time) {
        if ("x".equals(bus)) {
            return true;
        }
        if (time < 0) {
            return false;
        }
        long busNum = Long.parseLong(bus);
        return time - ((time / busNum) * busNum) == 0;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day13 solution = new Day13();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
