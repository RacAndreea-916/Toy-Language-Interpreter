package value;

import type.RefType;
import type.Type;

import java.util.Objects;

public class RefValue implements Value{

    private int address;
    private Type locationType;
    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress(){
        return address;
    }



    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefValue refValue = (RefValue) o;
        return address == refValue.address && Objects.equals(locationType, refValue.locationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, locationType);
    }

    public String toString(){

        return address +" " +  locationType.toString();

    }
}
