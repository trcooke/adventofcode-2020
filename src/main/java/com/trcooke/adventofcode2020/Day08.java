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

public class Day08 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay08");
        List<String> instructions = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            instructions.add(line);
        }
        int instructionIndex = 0;
        Set<Integer> executedInstructions = new HashSet<>();
        int acc = 0;
        do {
            executedInstructions.add(instructionIndex);
            String instruction = instructions.get(instructionIndex);
            String operation = instruction.split(" ")[0];
            int argument = Integer.parseInt(instruction.split(" ")[1]);
            switch (operation) {
                case "nop": instructionIndex++; break;
                case "acc": acc += argument; instructionIndex++; break;
                case "jmp": instructionIndex += argument; break;
            }
        } while (!executedInstructions.contains(instructionIndex));
        return acc;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay08");
        List<String> instructions = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            instructions.add(line);
        }
        int nextInstructionToModify = 0;
        while (true) {
            try {
                modifyInstruction(instructions, nextInstructionToModify);
                return findAcc(instructions);
            } catch (Exception e) {
                modifyInstruction(instructions, nextInstructionToModify);
                nextInstructionToModify++;
            }
        }
    }

    private void modifyInstruction(List<String> instructions, int nextInstructionToModify) {
        String instruction = instructions.get(nextInstructionToModify);
        if (instruction.startsWith("nop")) {
            String newInstruction = instruction.replace("nop", "jmp");
            instructions.set(nextInstructionToModify, newInstruction);
        } else if (instruction.startsWith("jmp")) {
            String newInstruction = instruction.replace("jmp", "nop");
            instructions.set(nextInstructionToModify, newInstruction);
        }
    }

    private int findAcc(List<String> instructions) throws Exception {
        int instructionIndex = 0;
        Set<Integer> executedInstructions = new HashSet<>();
        int acc = 0;
        do {
            executedInstructions.add(instructionIndex);
            String instruction = instructions.get(instructionIndex);
            String operation = instruction.split(" ")[0];
            int argument = Integer.parseInt(instruction.split(" ")[1]);
            switch (operation) {
                case "nop": instructionIndex++; break;
                case "acc": acc += argument; instructionIndex++; break;
                case "jmp": instructionIndex += argument; break;
            }
            if (instructionIndex == instructions.size()) {
                return acc;
            }
        } while (!executedInstructions.contains(instructionIndex));
        throw new Exception("Instructions are an infinite loop");
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day08 solution = new Day08();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
