package com.trcooke.adventofcode2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day18Test {

    Day18 day18 = new Day18();

    @Test
    public void evaluateExample1() {
        assertThat(day18.evaluate("1 + 2 * 3 + 4 * 5 + 6"), is(71));
    }

    @Test
    public void evaluateExample2() {
        assertThat(day18.evaluate("1 + (2 * 3) + (4 * (5 + 6))"), is(51));
    }

    @Test
    public void evaluateExample3() {
        assertThat(day18.evaluate("2 * 3 + (4 * 5)"), is(26));
    }

    @Test
    public void evaluateExample4() {
        assertThat(day18.evaluate("5 + (8 * 3 + 9 + 3 * 4 * 3)"), is(437));
    }

    @Test
    public void evaluateExample5() {
        assertThat(day18.evaluate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"), is(12240));
    }

    @Test
    public void evaluateExample6() {
        assertThat(day18.evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"), is(13632));
    }

}