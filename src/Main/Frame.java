package Main;

import org.w3c.dom.ranges.RangeException;
import java.util.ArrayList;

/**
 * Created by henrik on 20/05/15.
 */
public class Frame {

    public enum Types {
        STANDARD,
        STRIKE,
        SPARE,
        LASTSPARE,
        LASTSTRIKE
    };

    private ArrayList<Integer> _throws;
    private Types type;

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
        setType();
    }

    public Frame(int throwOne, int throwTwo, Types type) {

        _throws = new ArrayList<Integer>(2);

        if((throwOne + throwTwo) > 10) {
            throw new RangeException((short) -1, "Sum cannot be larger than ten");
        }

        if((throwOne < 0) || throwTwo < 0) {
            throw new RangeException((short) -1, "Value cannot be less than zero");
        }
        _throws.add(throwOne);
        _throws.add(throwTwo);
        this.type = type;
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

    public Types getType() {
        return type;
    }

    private void setType() {

        if(_throws.get(0) == 10) {

            type = Types.STRIKE;

        }else if((_throws.get(0) + _throws.get(1)) == 10){

            type = Types.SPARE;

        }else {

            type = Types.STANDARD;

        }
    }
}
