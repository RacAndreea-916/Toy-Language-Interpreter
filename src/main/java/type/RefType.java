package type;

import value.RefValue;
import value.Value;



public class RefType implements Type{

    private Type inner;

    public RefType(Type inner){
        this.inner = inner;
    }

    public Type getInner(){
        return this.inner;
    }
    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }

    public boolean equals(Object another){

        if(another instanceof RefType){
            return inner.equals(((RefType) another).getInner());

        }
        else
            return false;

    }

    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }
}
