package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay11");
        List<List<Character>> seatLayout = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            seatLayout.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }
        boolean seatingHasChanged = false;
        do {
            List<List<Character>> nextSeatLayout = new ArrayList<>();
            for (int i = 0; i < seatLayout.size(); i++ ) {
                List<Character> nextRow = new ArrayList<>();
                for (int j = 0; j < seatLayout.get(i).size(); j++) {
                    Character seat = seatLayout.get(i).get(j);
                    if (seat == 'L' && adjacentOccupiedSeats(i, j, seatLayout) == 0) {
                        nextRow.add('#');
                    } else if (seat == '#' && adjacentOccupiedSeats(i, j, seatLayout) >= 4) {
                        nextRow.add('L');
                    } else {
                        nextRow.add(seat);
                    }
                }
                nextSeatLayout.add(nextRow);
            }
//            printSeatLayout(nextSeatLayout);
//            System.out.println();
            seatingHasChanged = hasSeatingChanged(seatLayout, nextSeatLayout);
            seatLayout = nextSeatLayout;
        } while (seatingHasChanged);
        return countOccupiedSeats(seatLayout);
    }

    private int countOccupiedSeats(List<List<Character>> seatLayout) {
        int occupiedSeats = 0;
        for (List<Character> row : seatLayout) {
            for (Character seat : row) {
                if (seat == '#') {
                    occupiedSeats++;
                }
            }
        }
        return occupiedSeats;
    }

    private boolean hasSeatingChanged(List<List<Character>> seatLayout, List<List<Character>> nextSeatLayout) {
        for (int i = 0; i < seatLayout.size(); i++ ) {
            for (int j = 0; j < seatLayout.get(i).size(); j++ ) {
                if (seatLayout.get(i).get(j) != nextSeatLayout.get(i).get(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printSeatLayout(List<List<Character>> seatLayout) {
        for (List<Character> row : seatLayout) {
            for (Character col : row) {
                System.out.print(col);
            }
            System.out.println();
        }

    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay11");
        List<List<Character>> seatLayout = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            seatLayout.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }
        boolean seatingHasChanged = false;
        do {
            List<List<Character>> nextSeatLayout = new ArrayList<>();
            for (int i = 0; i < seatLayout.size(); i++ ) {
                List<Character> nextRow = new ArrayList<>();
                for (int j = 0; j < seatLayout.get(i).size(); j++) {
                    Character seat = seatLayout.get(i).get(j);
                    if (seat == 'L' && visibleOccupiedSeats(i, j, seatLayout) == 0) {
                        nextRow.add('#');
                    } else if (seat == '#' && visibleOccupiedSeats(i, j, seatLayout) >= 5) {
                        nextRow.add('L');
                    } else {
                        nextRow.add(seat);
                    }
                }
                nextSeatLayout.add(nextRow);
            }
//            printSeatLayout(nextSeatLayout);
//            System.out.println();
            seatingHasChanged = hasSeatingChanged(seatLayout, nextSeatLayout);
            seatLayout = nextSeatLayout;
        } while (seatingHasChanged);
        return countOccupiedSeats(seatLayout);
    }

    private long visibleOccupiedSeats(int i, int j, List<List<Character>> seatLayout) {
        List<Character> visibleSeats = new ArrayList<>();
        for(int x = - 1; x <= 1; x++ ) {
            for(int y = - 1; y <= 1; y++) {
                if (x != 0 || y != 0) {
                    Character nextVisibleSeat = nextVisibleSeat(i,j,x,y,seatLayout);
                    visibleSeats.add(nextVisibleSeat);
                }
            }
        }
        return visibleSeats.stream().filter(c -> c == '#').count();
    }

    private Character nextVisibleSeat(int i, int j, int x, int y, List<List<Character>> seatLayout) {
        Character nextSeat = getSeat(i + x, j + y, seatLayout);
        if (nextSeat == '.') {
            return nextVisibleSeat(i + x, j + y, x, y, seatLayout);
        }
        return nextSeat;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day11 solution = new Day11();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private long adjacentOccupiedSeats(int i, int j, List<List<Character>> seatLayout) {
        List<Character> adjacentSeats = new ArrayList<>();
        for(int x = i - 1; x <= i + 1; x++ ) {
            for(int y = j - 1; y <= j + 1; y++) {
                if (x != i || y != j) {
                    adjacentSeats.add(getSeat(x, y, seatLayout));
                }
            }
        }
        return adjacentSeats.stream().filter(c -> c == '#').count();
    }

    private Character getSeat(int x, int y, List<List<Character>> seatLayout) {
        try {
            return seatLayout.get(x).get(y);
        } catch (IndexOutOfBoundsException e) {
            return ' ';
        }
    }

}
