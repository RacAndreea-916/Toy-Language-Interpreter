package Statement;

import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import exp.Exp;
import type.StringType;
import type.Type;
import value.StringValue;
import value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStatement implements IStmt{

    private Exp exp;

    public OpenRFileStatement(Exp e){exp = e;}
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        Value filePathValue = exp.eval(state.getDictionary(), state.getHeap());

        if(!filePathValue.getType().equals(new StringType()))
            throw new MyException("not a string type");
        if(fileTable.isKey((StringValue) filePathValue))
            throw new MyException("this key is already in file table");

        try{
            BufferedReader fileBuffer = new BufferedReader(new FileReader(((StringValue)filePathValue).getVal()));
            fileTable.add((StringValue)filePathValue, fileBuffer);
        }catch (FileNotFoundException e){
            throw new MyException("file not found");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeExp.equals(new StringType())){
            return typeEnv;
        }else throw new MyException("Open FILE:the expression is not String Type");
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFileStatement(exp.deepCopy());
    }

    public String toString(){
        return "openRead(" + this.exp + ")\n";
    }

}
