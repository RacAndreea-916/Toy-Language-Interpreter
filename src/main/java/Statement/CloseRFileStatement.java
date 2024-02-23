package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Exceptions.NoKeyException;
import Model.MyIDictionary;
import State.PrgState;
import exp.Exp;
import type.StringType;
import type.Type;
import value.StringValue;
import value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStmt{

    private final Exp filePath;

    public CloseRFileStatement(Exp path){
        filePath = path;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {

        MyIDictionary<String, Value> symTable = state.getDictionary();
        MyIDictionary<StringValue, BufferedReader>fileTable = state.getFileTable();

        Value filePathValue = filePath.eval(symTable,state.getHeap() );

        if(!filePathValue.getType().equals(new StringType()))
            throw new MyException("not a string type");
        if(!fileTable.isKey((StringValue) filePathValue))
            throw new MyException("this key is already in file table");


        try {
            BufferedReader fileBuffer = fileTable.lookUp((StringValue) filePathValue);
            fileBuffer.close();
            fileTable.delete((StringValue) filePathValue);
        }catch(EmptyTableException | NoKeyException e)
        {
            throw new EmptyTableException();
        }catch (IOException io){
            throw new MyException("could not ceva");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = filePath.typeCheck(typeEnv);
        if(typeExp.equals(new StringType())){
            return typeEnv;
        }else throw new MyException("Open FILE:the expression is not String Type");
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFileStatement(filePath);
    }

    public String toString(){
        return "close(" + filePath + ")\n";
    }


}
