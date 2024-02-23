package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import exp.Exp;
import type.BoolType;
import type.IntType;
import type.Type;

import java.io.IOException;

public class DoWhileStatement implements IStmt{

    private IStmt statement;
    private Exp expression;

    public DoWhileStatement( Exp exp,IStmt stmt){
        this.statement=stmt;
        this.expression=exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        IStmt converted = new CompStmt(statement,new WhileStatement(expression,statement));
        state.getStack().push(converted);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = expression.typeCheck(typeEnv);
        if(type1.equals(new BoolType()))
            return typeEnv;
        else throw new MyException("do while statement is invalid");
    }

    @Override
    public IStmt deepCopy() {
        return new DoWhileStatement(expression.deepCopy(),statement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("do {%s} while (%s)", statement, expression);
    }
}
