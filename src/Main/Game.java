package Main;

import org.w3c.dom.ranges.RangeException;
import Main.Frame.Types;

/**
 * Created by henrik on 20/05/15.
 */
public class Game {

    private Frame[] frames;
    private int numFrames;
    private int extraThrow1;
    private int extraThrow2;

    public Game() {

        frames = new Frame[10];
        numFrames = 0;
        extraThrow1 = 0;
    }

    public void setExtraThrow1(int points) {

        this.extraThrow1 = points;

    }

    public void setExtraThrow2(int points) {

        this.extraThrow2 = points;

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
        Frame f;

        for(int i = 0; i < frames.length; i++) {

            f = frames[i];

            if(f.getType() == Types.STRIKE) {

                returnVal += strikeCalc(i);

            }else if(f.getType() == Types.SPARE) {

                returnVal += spareCalc(i, f);

            }else if(f.getType() == Types.LASTSPARE) {

                returnVal += f.getFrameScore();
                returnVal += extraThrow1;

            }else if(f.getType() == Types.LASTSTRIKE){

                returnVal += 10;
                returnVal += extraThrow1;
                returnVal += extraThrow2;

            }else {

                returnVal += f.getFrameScore();

            }
        }

        return returnVal;
    }

    private int strikeCalc(int index) {

        if(index < frames.length-2) {

            int returnVal = 0;
            if(frames[index + 1].getType() == Types.STRIKE) {

                returnVal += 20;
                returnVal += frames[index + 2].getThrowOne();

            }else {

                returnVal += 10;
                returnVal += frames[index + 1].getFrameScore();

            }
            return returnVal;
        }else if(index < frames.length-1) {

            int returnVal = 0;
            if(frames[index +1].getType() == Types.LASTSTRIKE) {

                returnVal += 20;
                returnVal += frames[index + 1].getFrameScore();
            }
            return returnVal;
        }
        return 0;
    }

    private int spareCalc(int index, Frame f) {

        if(index < frames.length-1) {

            return f.getFrameScore() + frames[index + 1].getThrowOne();

        }

        return 0;
    }
}
