package Model;

import Exceptions.EmptyListException;
import Exceptions.ListIndexException;

import java.util.List;

public interface MyIList<T> {

    public void add(T element);
    public List<T> getAll();

    public void delete(int index) throws ListIndexException,  EmptyListException;

    public String toString();

}
