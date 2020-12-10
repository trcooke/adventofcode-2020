package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day10Test {
    private Day10 day10 = new Day10();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day10.part1(), is(1700));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day10.part2(), is(12401793332096L));
    }

}