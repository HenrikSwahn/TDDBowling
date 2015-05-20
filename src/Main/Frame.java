package Main;

import java.util.ArrayList;

/**
 * Created by henrik on 20/05/15.
 */
public class Frame {

    private ArrayList<Integer> _throws;

    public Frame(int throwOne, int throwTwo) {

        _throws = new ArrayList<Integer>(2);
        _throws.add(throwOne);
        _throws.add(throwTwo);
    }

    public int getThrowOne() {
        return _throws.get(0);
    }

    public int getThrowTwo() {
        return _throws.get(1);
    }
}
