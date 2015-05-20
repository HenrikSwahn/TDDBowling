package Main;

/**
 * Created by henrik on 20/05/15.
 */
public class Game {

    private Frame[] frames;

    public Game() {

        frames = new Frame[10];
    }

    public int getNumberOfFrames() {

        return frames.length;
    }
}
