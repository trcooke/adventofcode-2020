package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day03Test {
    Day03 day03 = new Day03();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day03.part1(), is(270));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day03.part2(), is(2122848000));
    }

}