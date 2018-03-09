package Model.Dictionary;

import Exceptions.MyDictionaryException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<T1,T2> implements MyIDictionary<T1,T2>
{
    private Map<T1,T2> dict;

    public MyDictionary()
    {
        this.dict= new HashMap<>() ;
    }

    @Override
    public T2 lookup(T1 id) throws MyDictionaryException {
        if (dict.get(id) != null) {
            return dict.get(id);
        } else {
            throw new MyDictionaryException("The value does not exist!!");
        }
    }

    @Override
    public Map<T1,T2> getDict()
    {
        return dict;
    }

    @Override
    public void add(T1 id, T2 val)
    {
        dict.put(id,val);
    }

    @Override
    public void update(T1 id, T2 val)
    {
        dict.put(id,val);
    }

    @Override
    public void addall(Map<T1, T2> dict)
    {
        this.dict.putAll(dict);
    }

    @Override
    public void print()
    {
        for(T1 name:dict.keySet())
        {
            String key=name.toString();
            System.out.println(key+" " + dict.get(key));
        }
    }


    @Override
    public String toString()
    {
        String msg="";
        for (T1 name:dict.keySet())
        {
            String key=name.toString();
            msg+=key+"-->"+dict.get(key).toString()+" "+'\n';
        }
        return msg;
    }


    @Override
    public int isDefined(T1 id) {
        if (dict.get(id) != null)
            return 1;
        else
            return 0;
    }
}
