package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.ExeStack;
import Model.MyIDictionary;
import Model.MyIStack;
import State.PrgState;
import type.Type;
import value.Value;

import java.io.IOException;

public class ForkStatement implements IStmt{

    IStmt statement;

    public ForkStatement(IStmt fork){
        this.statement = fork;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {

        MyIStack<IStmt> stack = new ExeStack<>();

        MyIDictionary<String, Value>symTable = state.getDictionary();
        MyIDictionary<String, Value>copySymTable = symTable.copy();

        return new PrgState(stack, copySymTable, state.getList(), state.getFileTable(), state.getHeap(),PrgState.generateNewId(),this.statement);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        statement.typeCheck(typeEnv.copy());
        return typeEnv;

    }

    @Override
    public IStmt deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    public String toString(){
        return "fork("+statement.toString()+")";
    }
}
