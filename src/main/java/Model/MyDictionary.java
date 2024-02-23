package Model;

import Exceptions.EmptyTableException;
import Exceptions.NoKeyException;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{

    private final Dictionary<K, V> dictionary;

    public MyDictionary(){
        dictionary = new Hashtable<>();
    }
    @Override
    public void add(K key, V element) {
            dictionary.put(key, element);
    }

    @Override
    public void delete(K key) throws EmptyTableException, NoKeyException {
        if(dictionary.isEmpty())
            throw new EmptyTableException();
        if(!isKey(key))
            throw new NoKeyException();
        dictionary.remove(key);
    }

    @Override
    public V lookUp(K key) {
        return dictionary.get(key);
    }

    @Override
    public boolean isKey(K key) {

        Enumeration<K> keySet = dictionary.keys();
        while(keySet.hasMoreElements()){
            K k = keySet.nextElement();
            if(key == k)
                return true;
        }
        return false;
    }

    @Override
    public void update(K key, V newElem) {
        dictionary.put(key, newElem);
    }

    @Override
    public boolean isDefined() {
        return !dictionary.isEmpty();
    }

    public String toString(){
        StringBuilder st = new StringBuilder();
        Enumeration<K>enu = dictionary.keys();

         while(enu.hasMoreElements()){
             K k = enu.nextElement();
             st.append(k.toString()).append("<-");
             st.append(lookUp(k).toString()).append("\n");
         }

        return st.toString();
    }

    public Hashtable<K,V>getContent(){return (Hashtable<K, V>) dictionary;}

    @Override
    public MyIDictionary<K, V> copy() {
        MyIDictionary<K,V>newDictionary = new MyDictionary<>();
        Enumeration<K> keySet = dictionary.keys();
        
        while(keySet.hasMoreElements()){
            K k = keySet.nextElement();
            newDictionary.add(k, lookUp(k));
        }
        return newDictionary;
    }


}
