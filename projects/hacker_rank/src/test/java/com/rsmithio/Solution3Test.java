package com.rsmithio;


import java.util.*;
import org.junit.Assert;
import org.junit.Test;


public class Solution3Test {
    @Test
    public void testShouldCalculateSeriesWith_1_1_1() {
        ArrayList<Integer> series = Solution3.calculate_series(1, 1, 1);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(2);

        Assert.assertEquals(expected, series);
    }

    @Test
    public void testShouldCalculateSeriesWith_1_1_2() {
        ArrayList<Integer> series = Solution3.calculate_series(1, 1, 2);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(2);
        expected.add(4);

        Assert.assertEquals(expected, series);
    }

    @Test
    public void testShouldCalculateSeriesWith_5_3_5() {
        ArrayList<Integer> series = Solution3.calculate_series(5, 3, 5);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(8);
        expected.add(14);
        expected.add(26);
        expected.add(50);
        expected.add(98);

        Assert.assertEquals(expected, series);
    }

    @Test
    public void testShouldCalculateSeriesWith_0_2_10() {
        ArrayList<Integer> series = Solution3.calculate_series(0, 2, 10);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(2);
        expected.add(6);
        expected.add(14);
        expected.add(30);
        expected.add(62);
        expected.add(126);
        expected.add(254);
        expected.add(510);
        expected.add(1022);
        expected.add(2046);

        Assert.assertEquals(expected, series);
    }
}