package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day12Test {
    private Day12 day12 = new Day12();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day12.part1(), is(590));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day12.part2(), is(42013));
    }

}