package Model.Heap;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<T>
{
    private int key;
    private Map<Integer,T> heapmap;

    public MyHeap()
    {
        this.key=0;
        this.heapmap=new HashMap<>();
    }

    @Override
    public void add(T elem)
    {
        key++;
        heapmap.put(key,elem);
    }

    @Override
    public T get(int key) {
        return heapmap.get(key);
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public void update(int id, T elem) {
        heapmap.put(id,elem);
    }

    @Override
    public Map<Integer, T> getHeapmap() {
        return heapmap;
    }

    @Override
    public void setHeapmap(Map<Integer, T> heapmap) {
        this.heapmap = heapmap;
    }

    @Override
    public String toString()
    {
        String msg="";
        for(int i:heapmap.keySet())
        {
            msg+=i+ "-->" +heapmap.get(i)+" "+'\n';
        }
        return msg;
    }
}
