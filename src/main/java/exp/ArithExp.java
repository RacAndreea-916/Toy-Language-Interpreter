package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.IntType;
import type.Type;
import value.IntValue;
import value.Value;

public class ArithExp implements Exp {

    Exp e1;
    Exp e2;
    int op;//1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(Exp e1, Exp e2, int o){
        this.e1 = e1;
        this.e2 = e2;
        this.op = o;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,heap );
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4) {

                    if (n2 == 0) throw new MyException("divide by zero");
                    else return new IntValue(n1 / n2);
                }
            } else throw new MyException("second operand is not an integer");
        } else throw new MyException("first operand is not an integer");

        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {

        Type typ1, typ2;
        typ1 = e1.typeCheck(typeEnv);
        typ2 = e2.typeCheck(typeEnv);

        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new IntType();
            }else throw new MyException("the second operand is not int type");
        }else throw new MyException("first operand is not int type");


    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString(){
        String st;
        if(op==1) st="+";
        else if(op==2) st="-";
        else if(op==3) st="*";
        else st="/";
        return e1.toString() + st + e2.toString();
    }
}
