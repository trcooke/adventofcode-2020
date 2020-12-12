package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Day12 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay12");
        Ship ship = new Ship("E", 0, 0);
        for (String line; (line = reader.readLine()) != null;) {
            String action = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            ship.move(action, value);
        }
        return Math.abs(ship.hCoord) + Math.abs(ship.vCoord);
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay12");
        Ship ship = new ShipWithWaypoint("E", 0, 0, 10, 1);
        for (String line; (line = reader.readLine()) != null;) {
            String action = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            ship.move(action, value);
        }
        return Math.abs(ship.hCoord) + Math.abs(ship.vCoord);
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day12 solution = new Day12();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Ship {
        String direction;
        int hCoord;
        int vCoord;
        List<String> directions = Arrays.asList("N", "E", "S", "W");

        public Ship(String direction, int hCoord, int vCoord) {
            this.direction = direction;
            this.hCoord = hCoord;
            this.vCoord = vCoord;
        }

        public void move(String action, int value) {
            switch (action) {
                case "N": vCoord += value; break;
                case "S": vCoord -= value; break;
                case "E": hCoord += value; break;
                case "W": hCoord -= value; break;
                case "F": {
                    switch (direction) {
                        case "N": vCoord += value; break;
                        case "S": vCoord -= value; break;
                        case "E": hCoord += value; break;
                        case "W": hCoord -= value; break;
                    }
                    break;
                }
                case "L": direction = directions.get((directions.indexOf(direction) + ((360 - value) / 90)) % 4); break;
                case "R": direction = directions.get((directions.indexOf(direction) + (value / 90)) % 4); break;
            }
        }
    }

    private class ShipWithWaypoint extends Ship {
        private int hCoordWaypoint;
        private int vCoordWaypoint;

        public ShipWithWaypoint(String direction, int hCoord, int vCoord, int hCoordWaypoint, int vCoordWaypoint) {
            super(direction, hCoord, vCoord);
            this.hCoordWaypoint = hCoordWaypoint;
            this.vCoordWaypoint = vCoordWaypoint;
        }

        @Override
        public void move(String action, int value) {
            switch (action) {
                case "N": vCoordWaypoint += value; break;
                case "S": vCoordWaypoint -= value; break;
                case "E": hCoordWaypoint += value; break;
                case "W": hCoordWaypoint -= value; break;
                case "F": {
                    hCoord += hCoordWaypoint * value;
                    vCoord += vCoordWaypoint * value;
                    break;
                }
                case "L": {
                    switch (value) {
                        case 90: {
                            int hCoordWaypointCurrent = hCoordWaypoint;
                            int vCoordWaypointCurrent = vCoordWaypoint;
                            hCoordWaypoint = vCoordWaypointCurrent * -1;
                            vCoordWaypoint = hCoordWaypointCurrent;
                            break;
                        }
                        case 180: {
                            int hCoordWaypointCurrent = hCoordWaypoint;
                            int vCoordWaypointCurrent = vCoordWaypoint;
                            hCoordWaypoint = hCoordWaypointCurrent * -1;
                            vCoordWaypoint = vCoordWaypointCurrent * -1;
                            break;
                        }
                        case 270: {
                            int hCoordWaypointCurrent = hCoordWaypoint;
                            int vCoordWaypointCurrent = vCoordWaypoint;
                            hCoordWaypoint = vCoordWaypointCurrent;
                            vCoordWaypoint = hCoordWaypointCurrent * -1;
                            break;
                        }
                    }
                    break;
                }
                case "R": {
                    switch (value) {
                        case 90: {
                            int hCoordWaypointCurrent = hCoordWaypoint;
                            int vCoordWaypointCurrent = vCoordWaypoint;
                            hCoordWaypoint = vCoordWaypointCurrent;
                            vCoordWaypoint = hCoordWaypointCurrent * -1;
                            break;
                        }
                        case 180: {
                            int hCoordWaypointCurrent = hCoordWaypoint;
                            int vCoordWaypointCurrent = vCoordWaypoint;
                            hCoordWaypoint = hCoordWaypointCurrent * -1;
                            vCoordWaypoint = vCoordWaypointCurrent * -1;
                            break;
                        }
                        case 270: {
                            int hCoordWaypointCurrent = hCoordWaypoint;
                            int vCoordWaypointCurrent = vCoordWaypoint;
                            hCoordWaypoint = vCoordWaypointCurrent * -1;
                            vCoordWaypoint = hCoordWaypointCurrent;
                            break;
                        }
                    }
                    break;
                }
            }
        }

    }
}
