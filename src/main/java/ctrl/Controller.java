package ctrl;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIStack;
import State.Heap;
import State.PrgState;
import Statement.IStmt;
import repo.IRepository;
import value.RefValue;
import value.Value;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {

    private final IRepository repository;

    private ExecutorService executor;

    public Controller(IRepository repo){
        this.repository = repo;
    }

    public IRepository getRepository(){return this.repository;}


    private Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heap,PrgState prg) {
        Heap<Integer,Value> h = prg.getHeap();
        return h.getContent().entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heap.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v->{RefValue v1 = (RefValue) v;return v1.getAddress();})
                .collect(Collectors.toList());
}
    private List<Integer> getAddrFromHeap(Map<Integer,Value>heap) {
        return heap.values().stream()
                .filter(v -> v instanceof RefValue)
                .map(v->{RefValue v1 = (RefValue) v;return v1.getAddress();})
                .collect(Collectors.toList());
    }





//    public PrgState oneStep(PrgState state) throws MyException, EmptyTableException, IOException {
//        MyIStack<IStmt> stk = state.getStack();
//
//        if(stk.isEmpty()) throw new MyException("the stack is empty");
//
//        IStmt currentStmt = stk.pop();
//        return currentStmt.execute(state);
//    }


    public void oneStepForAllPrg(List<PrgState>prgList) throws InterruptedException {
        prgList.forEach(prg-> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<PrgState>>callList = prgList.stream().
                map((PrgState p)->(Callable<PrgState>)(p::oneStep)).collect(Collectors.toList());

        List<PrgState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future ->
                {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(executor.toString());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgList.addAll(newProgramsList);

        prgList.forEach(prg-> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        //executor.shutdown();
        repository.setPrgList(prgList);
    }
    public void allSteps() throws MyException, IOException, EmptyTableException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState>prgList = removeCompletedProgram(repository.getPrgList());
        while (!prgList.isEmpty()){
            Collection<Value> addresses = prgList.stream().
                    flatMap(program -> program.getDictionary().getContent().values().stream())
                    .collect(Collectors.toList());
            prgList.get(0).getHeap().setHeap(safeGarbageCollector(getAddrFromSymTable(addresses),getAddrFromHeap(prgList.get(0).getHeap().getContent()),prgList.get(0)));

            oneStepForAllPrg(prgList);
            prgList = removeCompletedProgram(prgList);
        }
        executor.shutdownNow();
        repository.setPrgList(prgList);

    }

    public void oneStep() throws MyException, IOException, EmptyTableException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState>prgList = removeCompletedProgram(repository.getPrgList());

        Collection<Value> addresses = prgList.stream().
                flatMap(program -> program.getDictionary().getContent().values().stream())
                .collect(Collectors.toList());
        prgList.get(0).getHeap().setHeap(safeGarbageCollector(getAddrFromSymTable(addresses),getAddrFromHeap(prgList.get(0).getHeap().getContent()),prgList.get(0)));

        oneStepForAllPrg(prgList);
        prgList = removeCompletedProgram(prgList);

        executor.shutdownNow();
        repository.setPrgList(prgList);

    }


    List<PrgState>removeCompletedProgram(List<PrgState>inPrgList){
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }


}
