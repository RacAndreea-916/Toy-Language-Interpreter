package Statement;

import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIStack;
import State.PrgState;
import type.Type;
import value.Value;

public class CompStmt implements IStmt {

    IStmt first;
    IStmt snd;

    public CompStmt(IStmt f, IStmt s){
        first = f;
        snd = s;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stk = state.getStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return snd.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), snd.deepCopy());
    }

    public String toString(){

        return "(" + first.toString() + ";" + snd.toString() + ");";
    }

}
