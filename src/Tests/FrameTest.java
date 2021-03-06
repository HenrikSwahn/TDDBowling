package Tests;

import Main.Frame;
import org.junit.Test;
import org.w3c.dom.ranges.RangeException;
import static org.junit.Assert.*;

public class FrameTest {

    @Test
    public void test_constructor_validValuesGiven() {

        Frame f = new Frame(4,5);

        int throwOne = f.getThrowOne();
        int throwTwo = f.getThrowTwo();

        assertEquals(4, throwOne);
        assertEquals(5, throwTwo);

    }

    @Test(expected = RangeException.class)
    public void test_constructor_toLargeValuesGiven() {

        Frame f = new Frame(8,7);
    }

    @Test(expected = RangeException.class)
    public void test_constructor_firstValueGivenNegative() {

        Frame f = new Frame(-1, 5);
    }

    @Test(expected = RangeException.class)
    public void test_constructor_secondValueGivenNegative() {

        Frame f = new Frame(2, -1);
    }

    @Test
    public void test_FrameScore_wantSumOfThrows() {

        Frame f = new Frame(2,6);

        int result = f.getFrameScore();
        assertEquals(8, result);
    }
}