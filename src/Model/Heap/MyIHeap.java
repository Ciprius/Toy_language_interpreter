package Model.Heap;

import java.util.Map;

public interface MyIHeap<T>
{
    public void add(T elem);
    public T get(int key);
    public int getKey();
    public void update(int id, T elem);
    public Map<Integer, T> getHeapmap();
    public void setHeapmap(Map<Integer, T> heapmap);
}
