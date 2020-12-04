package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Day01 {



    private int part1() throws IOException {
        BufferedReader reader = getInput("InputDay01");
        ArrayList<Integer> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(Integer.valueOf(line));
        }
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                if (input.get(i) + input.get(j) == 2020) {
                    return input.get(i) * input.get(j);
                }
            }
        }
        return 0;
    }

    private int part2() throws IOException {
        BufferedReader reader = getInput("InputDay01");
        ArrayList<Integer> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(Integer.valueOf(line));
        }
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                for (int k = j + 1; k < input.size(); k++) {
                    if (input.get(i) + input.get(j) + input.get(k) == 2020) {
                        return input.get(i) * input.get(j) * input.get(k);
                    }
                }
            }
        }
        return 0;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day01 solution = new Day01();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
