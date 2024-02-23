package Statement;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import type.Type;
import value.Value;

public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public VarDeclStmt(String n, Type t){
        name = n;
        typ = t;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIDictionary<String, Value>symTbl = state.getDictionary();

        if(symTbl.isKey(name)){
            throw new MyException("Variable is already defined");
        }

        Value defaultValue = typ.defaultValue();
        symTbl.add(name, defaultValue);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(name, typ);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, typ.deepCopy());
    }

    public String toString(){
        return typ.toString() + " " + name;
    }
}
