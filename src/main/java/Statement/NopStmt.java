package Statement;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import type.Type;

public class NopStmt implements IStmt{

    public String toString(){
        return "nop";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
