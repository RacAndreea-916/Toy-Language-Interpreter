package Statement.HeapStatements;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import State.Heap;
import State.PrgState;
import Statement.IStmt;
import exp.Exp;
import type.RefType;
import type.Type;
import value.RefValue;
import value.Value;

import java.io.IOException;

public class HeapWritingStatement implements IStmt {

    private String variableName;
    private Exp expression;

    public HeapWritingStatement(String name, Exp exp){
        variableName =  name;
        expression = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {

        MyIDictionary<String, Value> symTable = state.getDictionary();
        Heap<Integer, Value> heap = state.getHeap();
        if(symTable.isKey(variableName)){
            Value value = symTable.lookUp(variableName);
            Type type = value.getType();
            if(type instanceof RefType){
                int addr =((RefValue)value).getAddress();
                if(heap.isKey(addr)){
                    Value expValue = expression.eval(symTable,heap);
                    Type typeExp = expValue.getType();
                    //if(typeExp==((RefType)type).getInner()){
                        heap.update(addr,expValue);
                    //}else throw new MyException("type is not RefType");
                }else throw new MyException("the address is not a key in the heap");
            }else throw new MyException("type of variable name is not RefType");
        }else throw new MyException("variable name is not a key in the symTablehahah");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(variableName);
        Type typeExp = expression.typeCheck(typeEnv);

        if(typeVar.equals(new RefType(typeExp))){
            return typeEnv;
        }else throw new MyException("Write Heap statement: right hand side and left hand side are not the same type");
    }

    @Override
    public IStmt deepCopy() {
        return new HeapWritingStatement(variableName, expression.deepCopy());
    }

    public String toString(){
        return "wh("+variableName+","+expression.toString()+")";
    }
}
