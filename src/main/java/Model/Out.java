package Model;

import Exceptions.EmptyListException;
import Exceptions.ListIndexException;

import java.util.ArrayList;
import java.util.List;

public class Out<T> implements MyIList<T>{

    private final List<T> l;

    public Out(){

        l = new ArrayList<T>();
    }
    @Override
    public void add(T element)  {

        l.add(element);
    }

    @Override
    public List<T> getAll() {
        return l;
    }

    @Override
    public void delete(int index) throws ListIndexException, EmptyListException {
        if(index >= l.size())
            throw new ListIndexException();
        if(l.isEmpty())
            throw new EmptyListException();
        l.remove(index);
    }

    public String toString(){

        String st = "";
        for(T current:l)
            st += current.toString() + "\n";
        return st;
    }



}
