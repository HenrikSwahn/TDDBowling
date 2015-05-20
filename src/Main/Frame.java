package Main;

import org.w3c.dom.ranges.RangeException;
import java.util.ArrayList;

/**
 * Created by henrik on 20/05/15.
 */
public class Frame {

    private ArrayList<Integer> _throws;

    public Frame(int throwOne, int throwTwo) {

        _throws = new ArrayList<Integer>(2);

        if((throwOne + throwTwo) > 10) {
            throw new RangeException((short) -1, "Sum cannot be larger than ten");
        }

        if((throwOne < 0) || throwTwo < 0) {
            throw new RangeException((short) -1, "Value cannot be less than zero");
        }
        _throws.add(throwOne);
        _throws.add(throwTwo);
    }

    public int getThrowOne() {
        return _throws.get(0);
    }

    public int getThrowTwo() {
        return _throws.get(1);
    }

    public int getFrameScore() {
        return _throws.get(0) + _throws.get(1);
    }
}
