package value;

import type.BoolType;
import type.Type;

public class BoolValue implements Value{

    private boolean val;

    public BoolValue(boolean val){ this.val = val;}

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }

    public boolean getValue(){ return val;}

    public String toString(){return Boolean.toString(val);}

    public boolean equals(Object other){
        return other instanceof BoolValue && ((BoolValue)other).getValue() == val;
    }
}
