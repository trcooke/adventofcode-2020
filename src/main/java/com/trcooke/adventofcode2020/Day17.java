package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day17 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay17Test");
        List<Coords> activeCells = new ArrayList<>();
        int smallestX = 0;
        int largestX = 0;
        int smallestY = 0;
        int largestY = 0;
        int smallestZ = 0;
        int largestZ = 0;

        int y = 0;
        int z = 0;
        for (String line; (line = reader.readLine()) != null;) {
            System.out.println(line);
            int x = 0;
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    activeCells.add(new Coords(x,y,z));
                }
                largestX = x;
                x++;
            }
            largestY = y;
            y++;
        }
        for (int cycle = 1; cycle <= 6; cycle++ ) {
            System.out.println("Cycle " + cycle);

        }
        System.out.println(activeCells);
        return 0;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay17Test");
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
        Day17 solution = new Day17();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Coords {
        private final int x;
        private final int y;
        private final int z;

        public Coords(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }
}
