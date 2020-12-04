package com.trcooke.adventofcode2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day02Test {

    @Test
    public void part1_givenNotEnoughChars_shouldFail() {
        Day02 day02 = new Day02();
        assertThat(day02.isValidPart1Policy("1-3 a: abcde"), is(true));
    }

    @Test
    public void part1_givenEnoughChars_shouldPass() {
        Day02 day02 = new Day02();
        assertThat(day02.isValidPart1Policy("1-3 b: cdefg"), is(false));
    }
    @Test
    public void part1_givenTooManyChars_shouldFail() {
        Day02 day02 = new Day02();
        assertThat(day02.isValidPart1Policy("2-9 c: ccccccccc"), is(true));
    }

    @Test
    public void part2_givenOneMatchingChar_shouldPass() {
        Day02 day02 = new Day02();
        assertThat(day02.isValidPart2Policy("1-3 a: abcde"), is(true));
    }

    @Test
    public void part2_givenNoMatchingChars_shouldFail() {
        Day02 day02 = new Day02();
        assertThat(day02.isValidPart2Policy("1-3 b: cdefg"), is(false));
    }
    @Test
    public void part2_givenTwoMatchingChars_shouldFail() {
        Day02 day02 = new Day02();
        assertThat(day02.isValidPart2Policy("2-9 c: ccccccccc"), is(false));
    }
}