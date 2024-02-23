package repo;

import Exceptions.MyException;
import Model.MyIList;
import State.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repository implements IRepository{

    private String logFilePath;

    private List<PrgState>programStates;
   // private PrgState prg;

    public Repository(List<PrgState>programStates, String path){

        this.programStates = programStates;
        this.logFilePath = path;
    }
//public Repository(PrgState program, String path){
//
//    this.prg = program;
//    this.logFilePath = path;
//}
    //@Override
    //public PrgState getCurrentProgram() {
    //
//    public PrgState getCurrentProgram() {
////        return programStates.get(programStates.size()-1);
//        return programStates.get(0);
//    }


    @Override
    public void logPrgStateExec(PrgState program) throws MyException {

        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            logFile.println(program.toString());

        } catch (IOException e) {
            throw new MyException("could not open file");
        }
    }

    public void add(PrgState state){programStates.add(state);}

    @Override
    public List<PrgState> getPrgList() {
        return programStates;
    }

    @Override
    public void setPrgList(List<PrgState> newList) {
        programStates = newList;
    }

    @Override
    public PrgState getProgramStateWithId(int currentId) {
        for(PrgState pr : programStates)
            if(pr.getId() == currentId)
                return pr;
        return null;
    }
}
