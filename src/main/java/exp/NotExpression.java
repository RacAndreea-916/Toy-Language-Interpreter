package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.Type;
import value.BoolValue;
import value.Value;

public class NotExpression implements Exp{

    private Exp expression;

    public NotExpression(Exp exp){this.expression=exp;}
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {
        BoolValue value = (BoolValue) expression.eval(tbl,heap);
        if(!value.getValue())
            return new BoolValue(true);
        else return new BoolValue(false);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return expression.typeCheck(typeEnv);
    }

    @Override
    public Exp deepCopy() {
        return new NotExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "!"+this.expression.toString();
    }
}
