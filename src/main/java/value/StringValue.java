package value;

import type.StringType;
import type.Type;

public class StringValue implements Value{

    private String val;
    public StringValue(String v){val = v;}

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(val);
    }
    public String getVal(){return val;}

    public boolean equals(Object other){
        return other instanceof StringValue && ((StringValue)other).getVal().equals(val);}

    public String toString(){return val;}
}
