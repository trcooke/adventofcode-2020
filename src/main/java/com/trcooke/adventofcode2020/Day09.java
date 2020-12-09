package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day09 {

    Long part1() throws IOException {
        BufferedReader reader = getInput("InputDay09");
        int preambleLength = 25;
        List<Long> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(Long.parseLong(line));
        }
        for (int i = preambleLength; i < input.size(); i++ ) {
            if (!isValid(input.subList(i - preambleLength, i), input.get(i))) {
                return input.get(i);
            }
        }
        return 0L;
    }

    private boolean isValid(List<Long> preamble, Long nextNumber) {
        for (int i = 0; i < preamble.size() - 1; i++ ) {
            for (int j = i + 1; j < preamble.size(); j++ ) {
                if (preamble.get(i) + preamble.get(j) == nextNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    Long part2() throws IOException {
        BufferedReader reader = getInput("InputDay09");
        int preambleLength = 25;
        List<Long> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(Long.parseLong(line));
        }
        Long invalidNumber = 0L;
        for (int i = preambleLength; i < input.size(); i++ ) {
            List<Long> preamble = input.subList(i - preambleLength, i);
            if (!isValid(preamble, input.get(i))) {
                invalidNumber = input.get(i);
            }
        }
        List<Long> contiguousNumbers = contiguousNumbersAddingUpTo(input, invalidNumber);
        contiguousNumbers.sort(Long::compareTo);
        return contiguousNumbers.get(0) + contiguousNumbers.get(contiguousNumbers.size() - 1);
    }

    private List<Long> contiguousNumbersAddingUpTo(List<Long> input, Long invalidNumber) {
        for (int i = 0; i < input.size() - 1; i++ ) {
            for (int j = i + 1; j < input.size(); j++ ) {
                if (input.subList(i, j).stream().reduce(0L, Long::sum).equals(invalidNumber)) {
                    return input.subList(i, j);
                }
            }
        }
        return new ArrayList<>();
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day09 solution = new Day09();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
