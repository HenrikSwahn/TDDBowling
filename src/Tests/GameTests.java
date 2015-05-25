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

    @Test
    public void test_constructor_wantArrayWithTenSlots() {

        int result = g.getNumberOfFrames();
        assertEquals(10, result);
    }

    @Test
    public void test_insertFrame_wantToReturnTrue() {

        boolean result = g.appendFrame(new Frame(1,5));
        assertTrue(result);
    }

    @Test
    public void test_insertFrame_checkThatRightElementIsAdded() {

        Frame f = new Frame(1,1);
        g.appendFrame(f);
        assertEquals(f, g.getLastFrame());
    }

    @Test(expected = RangeException.class)
    public void test_insertFrame_toManyFrames() {

        for(int i = 0; i < 11; i++) {
            g.appendFrame(new Frame(1,1));
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_insertFrame_NullGiven() {

        for(int i = 0; i < 11; i++) {
            g.appendFrame(null);
        }
    }

    @Test
    public void test_GameScore_fullGameNoStrikeOrSpares() {

        Frame[] frames = {
                new Frame(1,5),
                new Frame(3,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,6)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(81, result);
    }

    @Test
    public void test_GameScore_fullGameAllZero() {

        Frame[] frames = {
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(0, result);
    }

    @Test(expected = RangeException.class)
    public void test_GameScore_tooFewFrames() {

        Frame[] frames = {
                new Frame(1,5),
                new Frame(3,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
        };

        for(int i = 0; i < 9; i++) {
            g.appendFrame(frames[i]);
        }

        g.getGameScore();
    }

    @Test
    public void test_GameScore_fullGameWithOneStrikeAndNoSpares() {

        Frame[] frames = {
                new Frame(10,0),
                new Frame(3,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,6)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(94, result);
    }

    @Test
    public void test_GameScore_fullGameWithNoStrikesAndOneSpare() {

        Frame[] frames = {
                new Frame(5,5),
                new Frame(3,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,6)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(88, result);
    }

    @Test
    public void test_GameScore_fullGameWithNoStrikesAndTwoSpare() {

        Frame[] frames = {
                new Frame(8,2),
                new Frame(5,5),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,6)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(98, result);
    }

    @Test
    public void test_GameScore_fullGameWithAllSpares() {

        Frame[] frames = {
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5),
                new Frame(5,5, Frame.Types.LASTSPARE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        g.setExtraThrow1(5);

        int result = g.getGameScore();
        assertEquals(150, result);
    }

    @Test
    public void test_GameScore_fullGameWithOneStrikeFollowedByOneSpare() {

        Frame[] frames = {
                new Frame(10,0),
                new Frame(4,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,6)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(103, result);
    }

    @Test
    public void test_GameScore_fullGameSpareFollowedByAStrike() {

        Frame[] frames = {
                new Frame(5,5),
                new Frame(10,0),
                new Frame(1,1),
                new Frame(1,1),
                new Frame(1,1),
                new Frame(1,1),
                new Frame(1,1),
                new Frame(1,1),
                new Frame(1,1),
                new Frame(1,1)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(48, result);
    }

    @Test
    public void test_GameScore_fullGameWithMultipleStrikesNoSpares() {

        Frame[] frames = {
                new Frame(10,0),
                new Frame(10,0),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,6)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        int result = g.getGameScore();
        assertEquals(112, result);
    }

    @Test
    public void test_GameScore_fullGameWithNoStrikesOneSpareAsLastFrame() {

        Frame[] frames = {
                new Frame(1,5),
                new Frame(3,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,8, Frame.Types.LASTSPARE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        g.setExtraThrow1(7);

        int result = g.getGameScore();
        assertEquals(90, result);
    }

    @Test
    public void test_GameScore_fullGameWithStrikeAsLastFrameNoSpares() {

        Frame[] frames = {
                new Frame(1,5),
                new Frame(3,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(10,0, Frame.Types.LASTSTRIKE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        g.setExtraThrow1(7);
        g.setExtraThrow2(2);

        int result = g.getGameScore();
        assertEquals(92, result);
    }

    @Test
    public void test_GameScore_fullGameAllZeroExceptLastFrameStrike() {

        Frame[] frames = {
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(10,0, Frame.Types.LASTSTRIKE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }
        g.setExtraThrow1(1);
        g.setExtraThrow2(1);
        int result = g.getGameScore();
        assertEquals(12, result);
    }

    @Test
    public void test_GameScore_fullGameAllZeroExceptLastFrameSpare() {

        Frame[] frames = {
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(0,0),
                new Frame(5,5, Frame.Types.LASTSPARE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }
        g.setExtraThrow1(1);
        int result = g.getGameScore();
        assertEquals(11, result);
    }

    @Test
    public void test_GameScore_BonusThrowIsAStrike() {

        Frame[] frames = {
                new Frame(1,5),
                new Frame(3,6),
                new Frame(7,2),
                new Frame(3,6),
                new Frame(4,4),
                new Frame(5,3),
                new Frame(3,3),
                new Frame(4,5),
                new Frame(8,1),
                new Frame(2,8, Frame.Types.LASTSPARE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        g.setExtraThrow1(10);

        int result = g.getGameScore();
        assertEquals(93, result);
    }

    @Test
    public void test_GameScore_perfectGame() {

        Frame[] frames = {
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0),
                new Frame(10,0, Frame.Types.LASTSTRIKE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        g.setExtraThrow1(10);
        g.setExtraThrow2(10);

        int result = g.getGameScore();
        assertEquals(300, result);
    }

    @Test
    public void test_GameScore_realGameTest() {

        Frame[] frames = {
                new Frame(6,3),
                new Frame(7,1),
                new Frame(8,2),
                new Frame(7,2),
                new Frame(10,0),
                new Frame(6,2),
                new Frame(7,3),
                new Frame(10,0),
                new Frame(8,0),
                new Frame(7,3, Frame.Types.LASTSPARE)
        };

        for(int i = 0; i < 10; i++) {
            g.appendFrame(frames[i]);
        }

        g.setExtraThrow1(10);

        int result = g.getGameScore();
        assertEquals(135, result);
    }
}