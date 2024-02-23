package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.BoolType;
import type.IntType;
import type.Type;
import value.BoolValue;
import value.IntValue;
import value.Value;

public class RelationalExp implements Exp{

    Exp expression1;
    Exp expression2;
    int operation;

    public RelationalExp(Exp exp1, Exp exp2, int op){
        expression1 = exp1;
        expression2 = exp2;
        operation = op;  //1 for '<'
                        //2 for '<='
                        //3 for '=='
                        //4 for '!='
                        //5 for '>'
                        //6 for '>='
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {
        Value v1, v2;
        v1 = expression1.eval(tbl,heap);
        if(v1.getType().equals(new IntType())){
            v2 = expression2.eval(tbl,heap );
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if(operation == 1) return new BoolValue(n1 < n2);
                if(operation == 2) return new BoolValue(n1 <= n2);
                if(operation == 3) return new BoolValue(n1 == n2);
                if(operation == 4) return new BoolValue(n1 != n2);
                if(operation == 5) return new BoolValue(n1 > n2);
                if(operation == 6) return new BoolValue(n1 >= n2);


            }
            else throw new MyException("second operand is not an integer type");

        }
        else throw new MyException("first operand is not an integer type");
        return null;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = expression1.typeCheck(typeEnv);
        typ2 = expression2.typeCheck(typeEnv);

        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new BoolType();
            }else throw new MyException("the second operand is not int in Relational Exp type");
        }else throw new MyException("first operand is not int in Relational Exp type");
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(expression1.deepCopy(), expression2.deepCopy(), operation);
    }

    public String toString(){
        String st;
        if(operation == 1) st = "<";
        else if(operation == 2) st = "<=";
        else if(operation == 3) st = "==";
        else if(operation == 4) st = "!=";
        else if(operation == 5) st = ">";
        else st = ">=";

        return expression1.toString() + st + expression2.toString();
    }
}
