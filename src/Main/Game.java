package Main;

import org.w3c.dom.ranges.RangeException;

/**
 * Created by henrik on 20/05/15.
 */
public class Game {

    private Frame[] frames;
    int numFrames;

    public Game() {

        frames = new Frame[10];
        numFrames = 0;
    }

    public int getNumberOfFrames() {

        return frames.length;
    }

    public boolean appendFrame(Frame newFrame) {

        if(numFrames < 10) {
            frames[numFrames++] = newFrame;
        }else {
            throw new RangeException((short) -1, "To many frames for one game");
        }
        return true;
    }

    public int getGameScore() {

        int returnVal = 0;

        for(Frame f: frames) {
            returnVal += f.getFrameScore();
        }

        return returnVal;
    }
}
