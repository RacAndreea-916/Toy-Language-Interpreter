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

public class NewStatement implements IStmt {

    private String variableName;
    private Exp expression;

    public NewStatement(String var, Exp exp){
        variableName = var;
        expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        MyIDictionary<String, Value>symTable = state.getDictionary();
        Heap<Integer, Value> heap = state.getHeap();

        if(symTable.isKey(variableName)) {
            Value valueInSymTable = symTable.lookUp(variableName);
            Type type = valueInSymTable.getType();
            if (type instanceof RefType) {

               Type locationType = ((RefType)type).getInner();
               Value v = expression.eval(symTable,heap);
               if(!v.getType().equals(locationType))
                   throw new MyException("is not the same type");
               else{
                   int free = heap.careIiNextFree();
                   heap.add(free,v);
                   symTable.update(variableName,new RefValue(free,locationType));
               }

            } else throw new MyException("the variable name is not reference type");
        }else throw new MyException("there is no variable like that in SymTable");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(variableName);
        Type typeExp = expression.typeCheck(typeEnv);

        if(typeVar.equals(new RefType(typeExp))){
            return typeEnv;
        }else throw new MyException("NEW statement: right hand side and left hand side are not the same type");
    }

    @Override
    public IStmt deepCopy() {
        return new NewStatement(variableName,expression.deepCopy());
    }

    public String toString(){
        return "new(" + variableName +", "+expression.toString()+")";
    }
}
