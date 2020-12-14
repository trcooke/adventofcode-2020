package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day14 {

    Long part1() throws IOException {
        BufferedReader reader = getInput("InputDay14");
        long maskTo1 = 0;
        long maskTo0 = 0;
        Map<Integer, Long> memory = new HashMap<>();
        for (String line; (line = reader.readLine()) != null;) {
            if (line.startsWith("mask")) {
                maskTo1 = Long.parseLong(line.split(" = ")[1].replaceAll("X", "0"), 2);
                maskTo0 = Long.parseLong(line.split(" = ")[1].replaceAll("X", "1"), 2);
            } else {
                int memLoc = Integer.parseInt(line.split(" = ")[0].replaceAll("[mem\\[\\]]", ""));
                long value = Long.parseLong(line.split(" = ")[1]);
                value |= maskTo1;
                value &= maskTo0;
                memory.put(memLoc, value);
            }
        }
        return memory.values().stream().reduce(Long::sum).get();
    }

    Long part2() throws IOException {
        BufferedReader reader = getInput("InputDay14Test2");
        String maskOriginal = "";
        List<String> masks = new ArrayList<>();
        Map<Integer, Long> memory = new HashMap<>();
        for (String line; (line = reader.readLine()) != null;) {
            if (line.startsWith("mask")) {
                maskOriginal = line.split(" = ")[1];
                masks.clear();
                masks.add("");
                System.out.println(maskOriginal);
                for (char c : maskOriginal.toCharArray()) {
                    List<String> newMasks = new ArrayList<>();
                    List<String> oldMasks = new ArrayList<>();
                    for (String mask : masks) {
                        oldMasks.add(mask);
                        if (c == 'X') {
                            newMasks.add(mask + "1");
                            newMasks.add(mask + "0");
                        } else {
                            newMasks.add(mask + c);
                        }
                    }
                    masks.removeAll(oldMasks);
                    masks.addAll(newMasks);
                }
            } else {
                long value = Long.parseLong(line.split(" = ")[1]);
                for (String mask : masks) {
                    int memLoc = Integer.parseInt(line.split(" = ")[0].replaceAll("[mem\\[\\]]", ""));
                    long maskTo1 = Long.parseLong(mask, 2);
                    System.out.printf("%s\n", Integer.toBinaryString(memLoc));
                    System.out.println(mask);
                    memLoc ^= maskTo1;
                    System.out.println(Integer.toBinaryString(memLoc) + " " + memLoc);
                    memory.put(memLoc, value);
                }
            }
        }
        return memory.values().stream().reduce(Long::sum).get();
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day14 solution = new Day14();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
