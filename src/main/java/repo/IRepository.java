package repo;

import Exceptions.MyException;
import State.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

   // PrgState getCurrentProgram();

    void logPrgStateExec(PrgState program) throws MyException, IOException;

    void add(PrgState state);

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState>newList);
    PrgState getProgramStateWithId(int currentId);

}
