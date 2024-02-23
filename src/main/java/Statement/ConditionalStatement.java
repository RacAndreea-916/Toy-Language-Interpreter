package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIStack;
import State.PrgState;
import exp.Exp;
import exp.VarExp;
import type.BoolType;
import type.Type;

import java.io.IOException;

public class ConditionalStatement implements IStmt{

    private String variable;
    private Exp expression1;
    private Exp expression2;
    private Exp expression3;
    public ConditionalStatement(String v,Exp e1,Exp e2,Exp e3){
        this.variable=v;
        this.expression1=e1;
        this.expression2=e2;
        this.expression3=e3;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        MyIStack<IStmt>exeStack = state.getStack();
        IStmt converted = new IfStmt(expression1,new AssignStmt(variable,expression2),new AssignStmt(variable,expression3));
        exeStack.push(converted);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type varType = new VarExp(variable).typeCheck(typeEnv);
        Type type1 = expression1.typeCheck(typeEnv);
        Type type2 = expression2.typeCheck(typeEnv);
        Type type3 = expression3.typeCheck(typeEnv);

        if(type1.equals(new BoolType())){
            if(type2.equals(varType) && type3.equals(varType))
                return typeEnv;
            else throw new MyException("the expressions and the variable in the conditional statement has not the same type");

        }else throw new MyException("the first expression in conditional statement is not Bol type");

    }

    @Override
    public IStmt deepCopy() {
        return new ConditionalStatement(variable,expression1.deepCopy(),expression2.deepCopy(),expression3.deepCopy());
    }

    @Override
    public String toString() {
        return variable +"="+expression1.toString()+"?"+expression2.toString()+":"+expression3.toString();

    }
}
