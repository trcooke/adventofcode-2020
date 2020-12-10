package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay10");
        List<Integer> adaptors = new ArrayList<>();
        adaptors.add(0);
        for (String line; (line = reader.readLine()) != null;) {
            adaptors.add(Integer.valueOf(line));
        }
        adaptors.sort(Integer::compareTo);
        int oneJoltDiffs = 0;
        int threeJoltDiffs = 1;
        for (int i = 0; i < adaptors.size() - 1; i++) {
            if (adaptors.get(i + 1) - adaptors.get(i) == 1) {
                oneJoltDiffs++;
            } else if (adaptors.get(i + 1) - adaptors.get(i) == 3) {
                threeJoltDiffs++;
            }
        }
        return oneJoltDiffs * threeJoltDiffs;
    }

    Long part2() throws IOException {
        BufferedReader reader = getInput("InputDay10");
        List<Integer> adaptors = new ArrayList<>();
        adaptors.add(0);
        for (String line; (line = reader.readLine()) != null;) {
            adaptors.add(Integer.valueOf(line));
        }
        adaptors.sort(Integer::compareTo);
        Map<Integer, Long> combos = new HashMap<>();
        Integer max = adaptors.stream().max(Integer::compareTo).get();
        combos.put(max + 3, 1L);
        for (int i = adaptors.size() - 1; i >= 0; i--) {
            Integer adaptor = adaptors.get(i);
            Long combosOfThis = 0L;
            for (int j = adaptor + 1; j <= adaptor + 3; j++) {
                combosOfThis += combos.getOrDefault(j, 0L);
            }
            combos.put(adaptor, combosOfThis);
        }
        Integer min = adaptors.stream().min(Integer::compareTo).get();
        return combos.get(min);
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day10 solution = new Day10();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
