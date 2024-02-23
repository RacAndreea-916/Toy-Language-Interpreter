package Exceptions;

public class EmptyTableException extends Exception{
    public EmptyTableException(){
        super("dictionary is empty!!");
    }
}
