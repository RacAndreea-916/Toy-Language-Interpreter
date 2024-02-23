package Statement;

import Exceptions.MyException;
import exp.Exp;
import Model.MyIDictionary;
import Model.MyIStack;
import State.PrgState;
import type.Type;
import value.Value;

public class AssignStmt implements IStmt{

    String id;
    Exp exp;

    public AssignStmt(String i, Exp e){
        id = i;
        exp = e;
    }

    public String toString(){
        return id + "=" + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stk = state.getStack();
        MyIDictionary<String, Value> symTbl = state.getDictionary();

        if (symTbl.isKey(id)) {
            value.Value val = exp.eval(symTbl, state.getHeap());
            Type typId = symTbl.lookUp(id).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else throw new MyException("declared type of variable" + id + "and type of" +
                    "the assigned expression do not match");
        } else throw new MyException("the used variable " + id + " was not declared before");
    return null;

    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(id);
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeVar.equals(typeExp)){
            return typeEnv;
        }else throw new MyException("Assigment:right hand side and left hand side are different types ");
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }
}
