package Tests;

import Main.Frame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FrameTest {

    private Frame f;

    @Before
    public void setup() {
        f = new Frame(4,5);
    }

    @Test
    public void test_constructor() {

        int throwOne = f.getThrowOne();
        int throwTwo = f.getThrowTwo();

        assertEquals(4, throwOne);
        assertEquals(5, throwTwo);

    }
}