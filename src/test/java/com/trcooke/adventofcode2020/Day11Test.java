package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day11Test {
    private Day11 day11 = new Day11();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day11.part1(), is(2093));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day11.part2(), is(1862));
    }

}