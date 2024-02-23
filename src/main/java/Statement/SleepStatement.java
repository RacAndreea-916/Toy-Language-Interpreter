package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import exp.ValueExp;
import exp.VarExp;
import type.IntType;
import type.Type;
import value.IntValue;

import java.io.IOException;

public class SleepStatement implements IStmt{

    private int value;

    public SleepStatement(int v){this.value=v;}
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        if(value != 0)
        {
            IStmt decl = new VarDeclStmt("v1",new IntType());
            IStmt all = new AssignStmt("v1",new ValueExp(new IntValue(value)));
            //state.getStack().push(,new SleepStatement(value-1));
            state.getStack().push(new CompStmt(decl,new CompStmt(all,new CompStmt(new PrintStmt(new VarExp("v1")),
                    new SleepStatement(value-1)))));
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new SleepStatement(value);
    }

    public String toString(){
        return "sleep("+this.value+")";
    }
}