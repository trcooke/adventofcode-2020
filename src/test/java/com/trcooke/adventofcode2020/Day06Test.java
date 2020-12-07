package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day06Test {
    private Day06 day06 = new Day06();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day06.part1(), is(6778));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day06.part2(), is(3406));
    }

}