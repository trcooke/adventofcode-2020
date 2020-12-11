package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DayTemplate {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay01");
        for (String line; (line = reader.readLine()) != null;) {
        }
        return 0;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay01");
        for (String line; (line = reader.readLine()) != null;) {
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
        DayTemplate solution = new DayTemplate();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
