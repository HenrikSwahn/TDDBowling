package Tests;

import Main.Game;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTests {

    @Test
    public void test_constructor_wantArrayWithTenSlots() {

        Game g = new Game();

        int result = g.getNumberOfFrames();
        assertEquals(10, result);
    }
}