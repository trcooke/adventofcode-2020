package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day02 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay02");
        int countValid = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if (isValidPart1Policy(line)) {
                countValid++;
            }
        }
        return countValid;
    }

    public boolean isValidPart1Policy(String line) {
        String[] policyAndPassword = line.split(":");
        String policy = policyAndPassword[0];
        String password = policyAndPassword[1].trim();
        String[] policyCountAndChar = policy.split(" ");
        String policyCount = policyCountAndChar[0];
        String policyChar = policyCountAndChar[1];
        int policyMinCount = Integer.parseInt(policyCount.split("-")[0]);
        int policyMaxCount = Integer.parseInt(policyCount.split("-")[1]);
        char[] passwordChars = password.toCharArray();
        Arrays.sort(passwordChars);
        String sortedPassword = new String(passwordChars);
        int indexOf = sortedPassword.indexOf(policyChar);
        int lastIndexOf = sortedPassword.lastIndexOf(policyChar);
        int charCount = lastIndexOf - indexOf + 1;
        return sortedPassword.contains(policyChar) && charCount >= policyMinCount && charCount <= policyMaxCount;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay02");
        int countValid = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if (isValidPart2Policy(line)) {
                countValid++;
            }
        }
        return countValid;
    }

    boolean isValidPart2Policy(String line) {
        String[] policyAndPassword = line.split(":");
        String policy = policyAndPassword[0];
        String password = policyAndPassword[1].trim();
        String[] policyCountAndChar = policy.split(" ");
        String policyCount = policyCountAndChar[0];
        char policyChar = policyCountAndChar[1].charAt(0);
        int policyPosition1Index = Integer.parseInt(policyCount.split("-")[0]) -1;
        int policyPosition2Index = Integer.parseInt(policyCount.split("-")[1]) -1;
        return password.charAt(policyPosition1Index) == policyChar ^ password.charAt(policyPosition2Index) == policyChar;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day02 solution = new Day02();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
