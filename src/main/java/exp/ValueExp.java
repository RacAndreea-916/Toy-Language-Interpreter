package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.Type;
import value.Value;

public class ValueExp implements Exp{

    private Value e;

    public ValueExp(Value  e){
        this.e = e;
    }


    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {
        return e;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }

    public String toString(){
        return e.toString();
    }
}
