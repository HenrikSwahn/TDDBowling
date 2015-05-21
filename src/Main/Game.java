package Main;

import org.w3c.dom.ranges.RangeException;
import Main.Frame.Types;

/**
 * Created by henrik on 20/05/15.
 */
public class Game {

    private Frame[] frames;
    private int numFrames;
    private int extraThrow;

    public Game() {

        frames = new Frame[10];
        numFrames = 0;
        extraThrow = 0;
    }

    public void setExtraThrow(int points) {

        this.extraThrow = points;

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

                if(i < frames.length-2) {

                    if (frames[i + 1].getType() == Types.STRIKE) {

                        returnVal += 20;
                        returnVal += frames[i + 2].getThrowOne();

                    } else {

                        returnVal += 10;
                        returnVal += frames[i + 1].getFrameScore();

                    }
                }

            }else if(f.getType() == Types.SPARE) {

                if(i < frames.length-1) {

                    returnVal += f.getFrameScore();
                    returnVal += frames[i + 1].getThrowOne();

                }

            }else if(f.getType() == Types.LASTSPARE) {

                returnVal += f.getFrameScore();
                returnVal += extraThrow;

            }else {

                returnVal += f.getFrameScore();

            }
        }

        return returnVal;
    }
}
