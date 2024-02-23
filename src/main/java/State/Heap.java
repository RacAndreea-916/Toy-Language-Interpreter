package State;

import Exceptions.EmptyTableException;
import Exceptions.NoKeyException;

import java.util.HashMap;
import java.util.Map;

public interface Heap<K,V>{

    void setHeap(Map<K,V> newHeap);

    void add(K key, V element);

    void delete(K key) throws EmptyTableException, NoKeyException;

    String toString();

    V lookUp(K key);

    boolean isKey(K key);

    void update(K key, V newElem);

    boolean isDefined();

    int careIiNextFree();

    Map<K,V>getContent();

}
