package type;

import value.StringValue;
import value.Value;

public class StringType implements Type{
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    public boolean equals(Object other){
        return other instanceof StringType;
    }

    public String toString(){return "string";}

}
