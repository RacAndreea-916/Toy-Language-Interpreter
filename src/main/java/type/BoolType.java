package type;

import value.BoolValue;
import value.Value;

public class BoolType implements Type{

    public boolean equals(Object other){
        return other instanceof BoolType;
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }

    public String toString(){return "bool";}

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
