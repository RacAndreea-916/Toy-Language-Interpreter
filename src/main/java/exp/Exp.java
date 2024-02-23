package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.Type;
import value.Value;

public interface Exp {
    value.Value eval(MyIDictionary<String, Value>tbl, Heap<Integer, Value> heap) throws MyException;

    Type typeCheck(MyIDictionary<String, Type>typeEnv) throws MyException;

    Exp deepCopy();
}
