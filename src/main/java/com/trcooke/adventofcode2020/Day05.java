package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day05 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay05");
        int highestSeatId = 0;
        for (String line; (line = reader.readLine()) != null;) {
            Seat seat = decodeBoardingPass(line);
            int seatId = seat.getId();
            if (seatId > highestSeatId) {
                highestSeatId = seatId;
            }
        }
        return highestSeatId;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay05");
        Set<Integer> allTakenSeats = new HashSet<>();
        for (String line; (line = reader.readLine()) != null;) {
            Seat seat = decodeBoardingPass(line);
            allTakenSeats.add(seat.getId());
        }
        for (Integer takenSeat : allTakenSeats) {
            if (allTakenSeats.contains(takenSeat + 2) && !allTakenSeats.contains(takenSeat + 1)) {
                return takenSeat + 1;
            }
            if (allTakenSeats.contains(takenSeat - 2) && !allTakenSeats.contains(takenSeat - 1)) {
                return takenSeat - 2;
            }
        }

        return 0;
    }

    Seat decodeBoardingPass(String seatCode) {
        int firstRow = 0;
        int lastRow = 127;
        int firstColumn = 0;
        int lastColumn = 7;
        String rowCode = seatCode.substring(0, 7);
        String columnCode = seatCode.substring(7);
        int row = 0;
        for (char c : rowCode.toCharArray()) {
            if (c == 'F') {
                lastRow = firstRow + ((lastRow - firstRow) / 2);
            } else if (c == 'B') {
                firstRow = firstRow + ((lastRow - firstRow) / 2) + 1;
            }
            row = firstRow;
        }
        int column = 0;
        for (char c : columnCode.toCharArray()) {
            if (c == 'L') {
                lastColumn = firstColumn + ((lastColumn - firstColumn) / 2);
            } else if (c == 'R') {
                firstColumn = firstColumn + ((lastColumn - firstColumn) / 2) + 1;
            }
            column = firstColumn;
        }
        return new Seat(row, column);
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day05 solution = new Day05();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    public static class Seat {
        private final int row;
        private final int column;

        public Seat(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getId() {
            return row * 8 + column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Seat seat = (Seat) o;
            return row == seat.row &&
                    column == seat.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return "Seat{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }
}
