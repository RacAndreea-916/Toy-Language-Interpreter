package Statement;

import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIList;
import exp.Exp;
import State.PrgState;
import type.Type;
import value.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp e){
        exp = e;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIList<Value> out = state.getList();
        try {
            Value val = exp.eval(state.getDictionary(), state.getHeap() );
            out.add(val);

        }catch(MyException e){
            throw new MyException("invalid expression");}


       // out.add(val);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public String toString(){return "print(" + exp.toString() + ")";}


}
