package Statement;

import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIStack;
import exp.Exp;
import State.PrgState;
import type.BoolType;
import type.Type;
import value.BoolValue;
import value.Value;

public class IfStmt implements IStmt{

    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp ex, IStmt t, IStmt e){
        exp=ex;thenS=t;
        elseS=e;
    }

    public String toString(){
        return "IF(" + exp.toString()+")THEN(" + thenS.toString() +
        ")ELSE(" + elseS.toString() + ")";}

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stk = state.getStack();
        Value cond = exp.eval(state.getDictionary(), state.getHeap() );

        if (!cond.getType().equals(new BoolType()))
            throw new MyException("this type is not bool");

        if(cond.getType().equals(new BoolType())){
            if(((BoolValue)cond).getValue()){
                stk.push(thenS);
            }
            else stk.push(elseS);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeExp.equals(new BoolType()))
        {
            thenS.typeCheck(typeEnv.copy());
            elseS.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else{
            throw new MyException("The condition of IF is not type Bool");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
