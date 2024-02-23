package Model;

import java.util.EmptyStackException;
import java.util.Stack;

public interface MyIStack<T> {

    public T pop() throws EmptyStackException;
    public void push(T element);

    boolean isEmpty();
    String toString();

    public void clear();
    Stack<T> getStack();

}
