package Statement;
import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import exp.Exp;
import type.IntType;
import type.RefType;
import type.StringType;
import type.Type;
import value.IntValue;
import value.StringValue;
import value.Value;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStmt {
    private final Exp filePath;
    private final String variableName;
    public ReadFileStatement(Exp exp, String var){
        filePath = exp;
        variableName = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTable = state.getDictionary();

        Value filePathValue = filePath.eval(symTable, state.getHeap() );

          if(!filePathValue.getType().equals(new StringType()))
            throw new MyException("not a string type");

        if(!symTable.isKey(variableName))
            throw new MyException("the variable name is not in the symbols table");

        try{
            BufferedReader fileBuffer = fileTable.lookUp((StringValue) filePathValue);
            String line = fileBuffer.readLine();
            if(line == null){
                symTable.update(variableName, new IntValue(0));
            }
            else
            {
                try{
                    symTable.update(variableName, new IntValue(Integer.parseInt(line)));

                }catch (Exception ignored){
                    throw new MyException("Cannot read value because EOF has been reached");
                }
            }

        }catch (IOException ex){
            throw new IOException("An error has occurred while reading\n");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(variableName);
        Type typeExp = filePath.typeCheck(typeEnv);

        if(typeVar.equals(new IntType())){
            if(typeExp.equals(new StringType())){
            return typeEnv;
        }else throw new MyException("Read file statement:file path is not string");
        }
        else throw new MyException("ReadFileStatement: variable is not int type");
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStatement(filePath.deepCopy(), variableName);
    }

    public String toString(){
        return "read(" + variableName + ");";
    }
}
