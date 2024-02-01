package com.example.smashrun;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals ;

public class PbCalculatorTests {

    @Test
    public void testCalculateTime_Simple() throws IOException {
        var myClass = new PbCalculator();
        var pb = myClass.GetFastestTime(
                new ArrayList<>(Arrays.asList(1.0, 2.0,4.0, 5.0)), new ArrayList<>(Arrays.asList(1.0, 2.0, 5.0, 5.1)),4);

        assertEquals(3.0, pb );
    }

    @Test
    public void testCalculateTime_Simple_other() throws IOException {
        var myClass = new PbCalculator();
        var pb = myClass.GetFastestTime(
                new ArrayList<>(Arrays.asList(1.0, 2.0,3.0, 4.0,31.0)), new ArrayList<>(Arrays.asList(0.0, 2.0, 5.0, 6.0,7.4)),7);

        assertEquals(30.0,pb);
    }

    @Test
    public void testCalculateTime_SlidingWindowCheck() throws IOException {
        var myClass = new PbCalculator();
        var pb = myClass.GetFastestTime(
                new ArrayList<>(Arrays.asList(1.0, 2.0,3.0, 4.0,31.0,40.5)), new ArrayList<>(Arrays.asList(0.0, 1.0, 3.0, 6.0,11.4,21.5)),10);

        assertEquals(9.5,pb);
    }
}
