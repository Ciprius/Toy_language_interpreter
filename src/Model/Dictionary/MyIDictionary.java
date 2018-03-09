package Model.Dictionary;

import Exceptions.MyDictionaryException;

import java.util.Map;

public interface MyIDictionary<T1,T2>
{
    public T2 lookup(T1 id) throws MyDictionaryException;
    public Map<T1,T2> getDict();
    public void add(T1 id, T2 val);
    public void update(T1 id, T2 val);
    public void addall(Map<T1, T2> dict);
    public void print();
    public int isDefined(T1 id);
}
