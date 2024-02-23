package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import type.Type;
import value.Value;

import java.io.IOException;

public interface IStmt {

    PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException;

    MyIDictionary<String, Type>typeCheck(MyIDictionary<String, Type>typeEnv) throws MyException;

    IStmt deepCopy();

}
