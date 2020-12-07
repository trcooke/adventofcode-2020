package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day01Test {

    Day01 day01 = new Day01();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day01.part1(), is(786811));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day01.part2(), is(199068980));
    }
}