package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day07Test {
    private Day07 day07 = new Day07();

    @Test
    public void part1CoveringTest() throws IOException {
        assertThat(day07.part1(), is(355));
    }

    @Test
    public void part2CoveringTest() throws IOException {
        assertThat(day07.part2(), is(5312));
    }

}