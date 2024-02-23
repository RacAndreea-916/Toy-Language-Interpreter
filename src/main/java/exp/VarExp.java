package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.Type;
import value.Value;

public class VarExp implements Exp{

    private final String id;

    public VarExp(String id){
        this.id = id;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {
        Value val = tbl.lookUp(id);
        if(val == null){
            throw new MyException("Variable " + id + " is not defined");
        }
        return val;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookUp(id);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }

    public String toString(){
        return id;
    }
}
