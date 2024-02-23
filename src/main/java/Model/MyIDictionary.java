package Model;

import Exceptions.EmptyTableException;
import Exceptions.NoKeyException;

import java.util.Hashtable;

public interface MyIDictionary<K, V> {

    void add(K key, V element);

    void delete(K key) throws EmptyTableException, NoKeyException;

    String toString();

    V lookUp(K key);

    boolean isKey(K key);

    void update(K key, V newElem);

    boolean isDefined();

    Hashtable<K,V>getContent();

    public MyIDictionary<K,V>copy();


}
