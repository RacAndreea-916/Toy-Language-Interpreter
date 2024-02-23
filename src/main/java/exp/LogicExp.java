package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.BoolType;
import type.IntType;
import type.Type;
import value.BoolValue;
import value.Value;

public class LogicExp implements Exp{

    Exp e1;
    Exp e2;
    int op;//1-&&, 2-||

    public LogicExp(Exp e1, Exp e2, int o){
        this.e1 = e1;
        this.e2 = e2;
        this.op = o;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {
        Value v1 = e1.eval(tbl,heap );
        Value v2 = e2.eval(tbl, heap);

        if(v1.getType().equals(new BoolType()) && v2.getType().equals(new  BoolType())) {
            BoolValue b1 = (BoolValue) v1;
            BoolValue b2 = (BoolValue) v2;
            boolean bool1 = b1.getValue();
            boolean bool2 = b2.getValue();

            switch (op){
                case 1:
                    return new BoolValue(bool1 && bool2);
                case 2:
                    return new BoolValue(bool1 || bool2);
                default:
                    throw new MyException("Invalid logical operation");
            }

        }else throw new MyException("Operand are not boolean");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);

        if(typ1.equals(new BoolType())){
            if(typ2.equals(new BoolType())){
                return new BoolType();
            }else throw new MyException("the second operand is not bool type");
        }else throw new MyException("first operand is not bool type");
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString(){
        String st;
        if(op==1) st=" && ";
        else if(op==2) st=" || ";
        else st=".";
        return e1.toString() + st + e2.toString();
    }
}
