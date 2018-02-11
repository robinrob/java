package com.rsmithio;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class Solution4Test {
    @Test
    public void testShouldReturnTypesFor1() {
        ArrayList<String> types = Solution4.get_types_for_x(1);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("byte");
        expected.add("short");
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForMinus150() {
        ArrayList<String> types = Solution4.get_types_for_x(-150);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("short");
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForMinus10ToPower13() {
        ArrayList<String> types = Solution4.get_types_for_x(-100000000000000L);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForByteMinValue() {
        ArrayList<String> types = Solution4.get_types_for_x(Byte.MIN_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("byte");
        expected.add("short");
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForByeMaxValue() {
        ArrayList<String> types = Solution4.get_types_for_x(Byte.MAX_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("byte");
        expected.add("short");
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForShortMinValue() {
        ArrayList<String> types = Solution4.get_types_for_x((long) Short.MIN_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("short");
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForShortMaxValue() {
        ArrayList<String> types = Solution4.get_types_for_x((long) Short.MAX_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("short");
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForIntMinValue() {
        ArrayList<String> types = Solution4.get_types_for_x((long) Integer.MIN_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForIntMaxValue() {
        ArrayList<String> types = Solution4.get_types_for_x((long) Integer.MAX_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("int");
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForLongMinValue() {
        ArrayList<String> types = Solution4.get_types_for_x(Long.MIN_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testShouldReturnTypesForLongMaxValue() {
        ArrayList<String> types = Solution4.get_types_for_x(Long.MAX_VALUE);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("long");

        Assert.assertEquals(expected, types);
    }

    @Test
    public void testCheckMinValues() {
        Assert.assertEquals((long) -Math.pow(2, 7), (long) Byte.MIN_VALUE);
        Assert.assertEquals((long) -Math.pow(2, 15), (long) Short.MIN_VALUE);
        Assert.assertEquals((long) -Math.pow(2, 31), (long) Integer.MIN_VALUE);
        Assert.assertEquals((long) -Math.pow(2, 63), (long) Long.MIN_VALUE);
    }

    @Test
    public void testCheckMaxValues() {
        Assert.assertEquals((long) Math.pow(2, 7)-1, (long) Byte.MAX_VALUE);
        Assert.assertEquals((long) Math.pow(2, 15)-1, (long) Short.MAX_VALUE);
        Assert.assertEquals((long) Math.pow(2, 31)-1, (long) Integer.MAX_VALUE);
        Assert.assertEquals((long) Math.pow(2, 63), (long) Long.MAX_VALUE);
    }
}