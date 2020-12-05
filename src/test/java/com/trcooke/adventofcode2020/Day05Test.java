package com.trcooke.adventofcode2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day05Test {

    Day05 day05 = new Day05();

    @Test
    public void boardingPassExample1() {
        assertThat(day05.decodeBoardingPass("FBFBBFFRLR"), is(new Day05.Seat(44, 5)));
    }
}