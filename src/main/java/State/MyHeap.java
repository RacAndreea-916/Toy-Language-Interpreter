package State;

import Exceptions.EmptyTableException;
import Exceptions.NoKeyException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K,V> implements Heap<K,V> {

    private Map<K,V> map;
    private int nextFree;



    public MyHeap(){
        map = new HashMap<K,V>();
        nextFree = 1;
    }

    @Override
    public void add(K key, V element) {
        map.put(key, element);

    }

    public void setHeap(Map<K,V>newHeap){
        this.map.clear();
        this.map=newHeap;}

    public void setNextFree(){
        this.nextFree = this.nextFree + 1;
    }
    public int careIiNextFree(){
        int pos = this.nextFree;
        this.setNextFree();
        return pos;
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public void delete(K key) throws EmptyTableException, NoKeyException {
        if(map.isEmpty())
            throw new EmptyTableException();
        if(!isKey(key))
            throw new NoKeyException();
        map.remove(key);
    }

    @Override
    public V lookUp(K key) {
        return map.get(key);
    }

    @Override
    public boolean isKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public void update(K key, V newElem) {
        map.put(key, newElem);
    }

    @Override
    public boolean isDefined() {
        return !map.isEmpty();
    }

    public String toString(){
        String st="";
        //st = "SymTable:\n";

        for(K k :map.keySet()){
            st = st + k.toString()+"<-";
            st = st + lookUp(k).toString()+"\n";
        }

        return st;
    }

}
