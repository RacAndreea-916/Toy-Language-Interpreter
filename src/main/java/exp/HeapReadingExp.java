package exp;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import type.RefType;
import type.Type;
import value.RefValue;
import value.Value;

public class HeapReadingExp implements Exp{
    private Exp expression;
    public HeapReadingExp(Exp exp){
        expression=exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, Heap<Integer, Value> heap) throws MyException {

        Value value = expression.eval(tbl, heap);
        if(value instanceof RefValue){
            int address = ((RefValue)value).getAddress();
            if(heap.isKey(address)){

                Value valueInHeap = heap.lookUp(address);
                return valueInHeap;


            }else throw new MyException("the address is not a key in the heap");


        }else throw new MyException("the value in not RefValue");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = expression.typeCheck(typeEnv);
        if(typ instanceof RefType){
            RefType reft = (RefType) typ;
            return reft.getInner();
        }else
            throw new MyException("the rH argument is not RefType");
    }

    @Override
    public Exp deepCopy() {
        return new HeapReadingExp(expression.deepCopy());
    }

    public String toString(){
        return "rH("+expression.toString()+")";
    }
}
