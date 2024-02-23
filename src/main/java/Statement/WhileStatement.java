package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIStack;
import State.PrgState;
import exp.Exp;
import type.BoolType;
import type.Type;
import value.BoolValue;
import value.Value;

import java.io.IOException;

public class WhileStatement implements IStmt{
    private Exp expression;
    private IStmt statement;

    public WhileStatement(Exp exp, IStmt is){
        expression = exp;
        statement = is;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        MyIStack<IStmt> stk = state.getStack();
        Value cond = expression.eval(state.getDictionary(), state.getHeap());

        if(!cond.getType().equals(new BoolType()))
            throw new MyException("the expression is not Bool");


        if(((BoolValue)cond).getValue()){
            stk.push(this);
            stk.push(statement);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = expression.typeCheck(typeEnv);
        if(typeExp.equals(new BoolType()))
        {
            statement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else{
            throw new MyException("The condition of WHILE is not type Bool");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    public String toString(){
        return "while("+expression.toString()+")"+statement.toString();
    }
}
