package value;

import type.IntType;
import type.Type;

public class IntValue implements Value{
    int val;

    public IntValue(int v){
        val=v;
    }

    public int getValue(){
        return val;
    }

    public String toString(){
        return Integer.toString(val);
    }
    @Override
    public Type getType() {

        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }

    public boolean equals(Object other){
        return other instanceof IntValue && ((IntValue)other).getValue() == val;
    }
}
