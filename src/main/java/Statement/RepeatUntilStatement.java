package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIStack;
import State.PrgState;
import exp.Exp;
import exp.NotExpression;
import type.BoolType;
import type.Type;

import java.io.IOException;

public class RepeatUntilStatement implements IStmt
{
    private Exp expression;
    private IStmt statement;

    public RepeatUntilStatement(IStmt st,Exp e){
        this.statement=st;
        this.expression=e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        MyIStack exeStack = state.getStack();
        IStmt  conv = new CompStmt(statement,new WhileStatement(new NotExpression(expression),statement));
        exeStack.push(conv);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);
        if(type.equals(new BoolType()))
        {
            statement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new MyException("expression in repeat until must be Bool type!");

    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntilStatement(statement.deepCopy(),expression.deepCopy());
    }

    public String toString(){
        return "repeat{"+this.statement.toString()+"}until "+this.expression.toString();

    }
}
