package com.trcooke.adventofcode2020;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day15Test {

    Day15 day15 = new Day15();

    @Test
    public void getNumberSpokenExample1() {
        assertThat(day15.getNumberSpoken(Arrays.asList(0,3,6), 2020), is(436));
    }

    @Test
    public void getNumberSpokenExample2() {
        assertThat(day15.getNumberSpoken(Arrays.asList(1,3,2), 2020), is(1));
    }

    @Test
    public void getNumberSpokenExample3() {
        assertThat(day15.getNumberSpoken(Arrays.asList(2,1,3), 2020), is(10));
    }

    @Test
    public void getNumberSpokenExample4() {
        assertThat(day15.getNumberSpoken(Arrays.asList(1,2,3), 2020), is(27));
    }

    @Test
    public void getNumberSpokenExample5() {
        assertThat(day15.getNumberSpoken(Arrays.asList(2,3,1), 2020), is(78));
    }

    @Test
    public void getNumberSpokenExample6() {
        assertThat(day15.getNumberSpoken(Arrays.asList(3,2,1), 2020), is(438));
    }

    @Test
    public void getNumberSpokenExample7() {
        assertThat(day15.getNumberSpoken(Arrays.asList(3,1,2), 2020), is(1836));
    }

    @Test
    public void getNumberSpokenExample8() {
        assertThat(day15.getNumberSpoken(Arrays.asList(0,3,6), 30000000), is(175594));
    }

    @Test
    public void getNumberSpokenExample9() {
        assertThat(day15.getNumberSpoken(Arrays.asList(1,3,2), 30000000), is(2578));
    }

    @Test
    public void getNumberSpokenExample10() {
        assertThat(day15.getNumberSpoken(Arrays.asList(2,1,3), 30000000), is(3544142));
    }

    @Test
    public void getNumberSpokenExample11() {
        assertThat(day15.getNumberSpoken(Arrays.asList(1,2,3), 30000000), is(261214));
    }

    @Test
    public void getNumberSpokenExample12() {
        assertThat(day15.getNumberSpoken(Arrays.asList(2,3,1), 30000000), is(6895259));
    }

    @Test
    public void getNumberSpokenExample13() {
        assertThat(day15.getNumberSpoken(Arrays.asList(3,2,1), 30000000), is(18));
    }

    @Test
    public void getNumberSpokenExample14() {
        assertThat(day15.getNumberSpoken(Arrays.asList(3,1,2), 30000000), is(362));
    }
}