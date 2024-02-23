package Model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class ExeStack<T> implements MyIStack<T>{


    //private final Stack<T> stack = new Stack<>();
    private final Stack<T>stack = new Stack<T>();
//    public MyStack(){
//        stack ;
//    }

    @Override
    public T pop() throws EmptyStackException {
       if(stack.isEmpty())
           throw new EmptyStackException();
       return stack.pop();
    }

    @Override
    public void push(T element) {
        stack.push(element);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public String toString(){

        StringBuilder st = new StringBuilder();
        List<T> str = new ArrayList<>(stack);
        for(int i = str.size()-1;i>=0;i--) {

            String current = str.get(i).toString();
            st.append(current).append("\n");
        }
        return st.toString();
    }

    public void clear(){stack.clear();}

    public Stack<T>getStack(){return this.stack;}




//    private class Node<T>{
//
//        T info;
//        Node<T> next;
//        Node(){ info = null; next = null; }
//
//        Node(T info, Node<T> next){
//            this.info = info;
//            this.next = next;
//        }
//
//
//    }
//
//    private Node<E> head;
//
//    Stack(){
//        this.head = null;
//    }
//
//    public void push(E element){
//        this.head = new Node<E>(element, head);
//    }
//
//    public E pop(){
//        E cap = head.info;
//        head = head.next;
//        return cap;
//
//    }
//
//    public boolean isEmpty(){
//        return head == null;
//    }



}
