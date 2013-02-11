package uk.co.mrrrobinsmith.binarychop.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import uk.co.mrrobinsmith.binarychop.app.BinaryChopper;
import uk.co.mrrobinsmith.binarychop.app.Chopper;

public class TestBinaryChopper {
    Chopper chopper;

    @Before
    public void setup() {
        chopper = new BinaryChopper();
    }

    private void performTest(final int intToFind, final int[] sortedInts, final int expectedIndex) {
        final int index = chopper.chop(intToFind, sortedInts);

        assertThat(index, equalTo(expectedIndex));
    }

    @Test
    public void shouldNotFindIntGivenNullArray() {
        performTest(1, null, -1);
    }

    @Test
    public void shouldNotFindIntGivenEmptyArray() {
        performTest(1, new int[] {}, -1);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize1ContainingInt() {
        performTest(1, new int[] { 1 }, 0);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize3NotContainingIntLessThanFirstElement() {
        performTest(0, new int[] { 1, 3, 5 }, -1);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize3NotContainingIntGreatherThanFirstElement() {
        performTest(2, new int[] { 1, 3, 5 }, -1);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize3NotContainingIntGreaterThanMiddleElement() {
        performTest(4, new int[] { 1, 3, 5 }, -1);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize3NotContainingIntGreaterThanLastElement() {
        performTest(6, new int[] { 1, 3, 5 }, -1);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize3ContainingIntAtStart() {
        performTest(1, new int[] { 1, 3, 5 }, 0);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize3ContainingIntInMiddle() {
        performTest(3, new int[] { 1, 3, 5 }, 1);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize3ContainingIntAtEnd() {
        performTest(3, new int[] { 1, 3, 5 }, 2);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize4NotContainingIntLessThanFirstElement() {
        performTest(0, new int[] { 1, 3, 5, 7 }, -1);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize4NotContainingIntGreatherThanFistElement() {
        performTest(2, new int[] { 1, 3, 5, 7 }, -1);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize4NotContainingIntGreaterThanSecondElement() {
        performTest(4, new int[] { 1, 3, 5, 7 }, -1);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize4NotContainingIntGreaterThanThirdElement() {
        performTest(6, new int[] { 1, 3, 5, 7 }, -1);
    }

    @Test
    public void shouldNotFindIntGivenArrayOfSize4NotContainingIntGreaterThanLastElement() {
        performTest(8, new int[] { 1, 3, 5, 7 }, -1);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize4ContainingIntAtStart() {
        performTest(1, new int[] { 1, 3, 5, 7 }, 0);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize4ContainingIntAtIndex2() {
        performTest(3, new int[] { 1, 3, 5, 7 }, 1);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize4ContainingIntAtIndex3() {
        performTest(5, new int[] { 1, 3, 5, 7 }, 2);
    }

    @Test
    public void shouldFindIntGivenArrayOfSize4ContainingIntAtIndex4() {
        performTest(7, new int[] { 1, 3, 5, 7 }, 3);
    }
}
