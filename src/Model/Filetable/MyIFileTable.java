package Model.Filetable;

import java.util.Map;

public interface MyIFileTable<T>
{
    public void add(T elem);
    public int getkey();
    public T get(int i);
    public void remove(int key);
    public Map<Integer, T> getFileT();
    //public boolean Valid(String filename) throws MyFileTableException;

}
