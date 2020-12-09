package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day09Test {
    private Day09 day09 = new Day09();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day09.part1(), is(21806024L));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day09.part2(), is(2986195L));
    }

}