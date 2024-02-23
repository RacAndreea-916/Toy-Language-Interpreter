package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIStack;
import State.PrgState;
import exp.Exp;
import exp.RelationalExp;
import exp.VarExp;
import type.IntType;
import type.Type;

import java.io.IOException;

public class ForStatement implements IStmt{

    private String variable;
    private Exp expression1;
    private Exp expression2;
    private Exp expression3;
    private IStmt statement;
    public ForStatement(String var,Exp e1,Exp e2,Exp e3,IStmt stmt) {
        this.variable=var;
        this.expression1=e1;
        this.expression2=e2;
        this.expression3=e3;
        this.statement=stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        MyIStack<IStmt>exeStack = state.getStack();
        IStmt converted = new CompStmt(new AssignStmt("v",expression1),
                new WhileStatement(new RelationalExp(new VarExp("v"),expression2,1),
                        new CompStmt(statement,new AssignStmt("v",expression3))));
        exeStack.push(converted);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = expression1.typeCheck(typeEnv);
        Type type2 = expression2.typeCheck(typeEnv);
        Type type3 = expression3.typeCheck(typeEnv);

        if(type1.equals(new IntType()) && type2.equals(new IntType()) && type3.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("The for statement is invalid!");
    }

    @Override
    public IStmt deepCopy() {
        return new ForStatement(variable,expression1.deepCopy(),expression2.deepCopy(),expression3.deepCopy(),statement.deepCopy());
    }

    public String toString(){
        return String.format("for(%s=%s; %s<%s; %s=%s) {%s}", variable, expression1, variable, expression2, variable, expression3, statement);
    }
}
