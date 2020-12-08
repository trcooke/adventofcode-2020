package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day08Test {
    private Day08 day08 = new Day08();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day08.part1(), is(1941));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day08.part2(), is(2096));
    }

}