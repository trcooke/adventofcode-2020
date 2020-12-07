package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day04Test {
    Day04 day04 = new Day04();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day04.part1(), is(228));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day04.part2(), is(175));
    }


}