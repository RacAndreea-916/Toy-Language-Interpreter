package type;

import value.BoolValue;
import value.IntValue;
import value.Value;

public class IntType implements Type{

    public boolean equals(Object another){
        return another instanceof IntType;
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }

    public String toString(){return "int";}

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
