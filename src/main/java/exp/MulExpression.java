package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.IntType;
import type.Type;
import value.Value;

public class MulExpression implements Exp{

    private Exp expression1;
    private Exp expression2;
    public MulExpression(Exp e1,Exp e2){this.expression1=e1;this.expression2=e2;}
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {
        Exp converted = new ArithExp(new ArithExp(expression1,expression2,3),
                new ArithExp(expression1,expression2,1),2);
        return converted.eval(tbl,heap);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = expression1.typeCheck(typeEnv);
        typ2 = expression2.typeCheck(typeEnv);

        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new IntType();
            }else throw new MyException("the second operand is not int type");
        }else throw new MyException("first operand is not int type");
    }

    @Override
    public Exp deepCopy() {
        return new MulExpression(expression1.deepCopy(),expression2.deepCopy());
    }

    @Override
    public String toString() {
        return "MUL("+expression1.toString()+","+expression2.toString()+")";
    }
}
