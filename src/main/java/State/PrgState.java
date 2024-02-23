package State;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import Model.MyIList;
import Model.MyIStack;
import Statement.IStmt;
import value.StringValue;
import value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {

    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<value.Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;

    private int id;
    private static int nextId = 0;

    public static synchronized int generateNewId(){
        return nextId++;
    }

    private final Heap<Integer,Value> heap;

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    private final IStmt originalProgram;

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, value.Value>symtbl, MyIList<value.Value>ot, MyIDictionary<StringValue, BufferedReader>fileT, Heap<Integer, Value>heap, int id, IStmt prg){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = fileT;
        this.heap=heap;
        originalProgram = prg.deepCopy();
        stk.push(prg);
        this.id = id;

    }

    public MyIStack<IStmt> getStack(){ return exeStack;}

    public void setExeStack(MyIStack<IStmt>newStack){this.exeStack=newStack;}

    public MyIList<value.Value> getList(){return out;}

    public MyIDictionary<String, value.Value> getDictionary(){return symTable;}
    public MyIDictionary<StringValue, BufferedReader> getFileTable(){return fileTable;}

    public Heap<Integer,Value>getHeap(){return heap;}

    public int getId(){return id;}

    public String toString(){
        return id+"\nEXECUTION STACK:\n"+exeStack.toString() + "SYMBOL TABLE:\n" + symTable.toString() + "OUT:\n" + out.toString()  + "FILE TABLE:\n"+ fileTable.toString()+  "HEAP:\n"+ heap.toString()+"\n";
    }

    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, EmptyTableException, IOException {
        if(exeStack.isEmpty()) throw new MyException("the program stack is empty");
        IStmt currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }
}
