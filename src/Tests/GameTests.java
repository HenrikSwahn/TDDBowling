package Tests;

import Main.Frame;
import Main.Game;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.ranges.RangeException;

import static org.junit.Assert.*;

public class GameTests {

    private Game g;
    @Before
    public void setup() {
        g = new Game();
    }

    //Testing Game user story
    @Test
    public void test_constructor_wantArrayWithTenSlots() {

        int result = g.getNumberOfFrames();
        assertEquals(10, result);
    }

    //Testing Game user story
    @Test
    public void test_insertFrame_wantToReturnTrue() {

        boolean result = g.appendFrame(new Frame(1,5));
        assertTrue(result);
    }

    //Testting Game user story
    @Test(expected = RangeException.class)
    public void test_insertFrame_toManyFrames() {

        for(int i = 0; i < 11; i++) {
            g.appendFrame(new Frame(1,1));
        }
    }
}