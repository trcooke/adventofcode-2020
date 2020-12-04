package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day03 {

    private int part1() throws IOException {
        BufferedReader reader = getInput("InputDay03");
        int positionToTheRight = 0;
        int treeCount = 0;
        for (String line; (line = reader.readLine()) != null;) {
            int indexToTheRight = positionToTheRight % 31;
            if (line.charAt(indexToTheRight) == '#') {
                treeCount++;
            }
            positionToTheRight = positionToTheRight + 3;
        }
        return treeCount;
    }

    private int part2() throws IOException {
        BufferedReader reader = getInput("InputDay03");
        ArrayList<String> slopeGrid = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            slopeGrid.add(line);
        }

        List<TraversalRule> rules = Arrays.asList(
                new TraversalRule(1, 1),
                new TraversalRule(3, 1),
                new TraversalRule(5, 1),
                new TraversalRule(7, 1),
                new TraversalRule(1, 2)
        );
        int sumOfTreeCounts = 1;
        for (TraversalRule rule : rules) {
            int positionToTheRight = 0;
            int positionDown = 0;
            int treeCount = 0;
            while (positionDown < slopeGrid.size()) {
                int indexToTheRight = positionToTheRight % 31;
                if (slopeGrid.get(positionDown).charAt(indexToTheRight) == '#') {
                    treeCount++;
                }
                positionToTheRight += rule.getMoveRightBy();
                positionDown += rule.getMoveDownBy();
            }
            sumOfTreeCounts *= treeCount;
        }

        return sumOfTreeCounts;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day03 solution = new Day03();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class TraversalRule {
        private int moveRightBy;
        private int moveDownBy;

        public TraversalRule(int moveRightBy, int moveDownBy) {
            this.moveRightBy = moveRightBy;
            this.moveDownBy = moveDownBy;
        }
        public int getMoveRightBy() {
            return moveRightBy;
        }

        public int getMoveDownBy() {
            return moveDownBy;
        }
    }
}
