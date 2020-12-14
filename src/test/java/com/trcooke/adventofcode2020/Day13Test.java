package com.trcooke.adventofcode2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day13Test {

    Day13 day13 = new Day13();

    @Test
    public void startTimeExample1() {
        String[] input = "7,13,x,x,59,x,31,19".split(",");
        assertThat(day13.findStartTime(input, 0), is(1068781L));
    }

    @Test
    public void startTimeExample2() {
        String[] input = "17,x,13,19".split(",");
        assertThat(day13.findStartTime(input, 0), is(3417L));
    }

    @Test
    public void startTimeExample3() {
        String[] input = "67,7,59,61".split(",");
        assertThat(day13.findStartTime(input, 0), is(754018L));
    }

    @Test
    public void startTimeExample4() {
        String[] input = "67,x,7,59,61".split(",");
        assertThat(day13.findStartTime(input, 0), is(779210L));
    }

    @Test
    public void startTimeExample5() {
        String[] input = "67,7,x,59,61".split(",");
        assertThat(day13.findStartTime(input, 0), is(1261476L));
    }

    @Test
    public void startTimeExample6() {
        String[] input = "1789,37,47,1889".split(",");
        assertThat(day13.findStartTime(input, 0), is(1202161486L));
    }

    @Test
    public void startTimeExample7() {
        String[] input = "1,100".split(",");
        assertThat(day13.findStartTime(input, 0), is(99L));
    }

    @Test
    public void startTimeExample8() {
        String[] input = "1,100000000000000".split(",");
        assertThat(day13.findStartTime(input, 0), is(99999999999999L));
    }

    @Test
    public void startTimeExample9() {
        String[] input = "2,11".split(",");
        assertThat(day13.findStartTime(input, 0), is(10L));
    }
}