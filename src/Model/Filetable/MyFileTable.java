package Model.Filetable;

import Model.Statments.Tuple;

import java.util.HashMap;
import java.util.Map;

public class MyFileTable<T> implements MyIFileTable<T>
{


    private Map<Integer,T> fileT;
    private int idx;
    //private Tuple<T> tpl;

    public MyFileTable()
    {
        this.fileT=new HashMap<>();
        this.idx=0;
    }

    @Override
    public void add(T elem)
    {
        idx++;
        //Tuple<T> tlp= new Tuple<T>(elem,bf);

        fileT.put(idx,elem);
    }

    @Override
    public int getkey() {
        return idx;
    }

    @Override
    public T get(int i) {
        return fileT.get(i);
    }

    @Override
    public void remove(int key)
    {
        fileT.remove(key);
    }


    @Override
    public String toString()
    {
        String msg="";
        for(int i:fileT.keySet())
        {
            Tuple tlp= (Tuple) fileT.get(i);
            msg+=i+ " " + tlp.getName()+'\n';
        }
        return msg;
    }

    public Map<Integer, T> getFileT()
    {
        return fileT;
    }
    /*@Override
    public boolean Valid(String filename) throws MyFileTableException {
        for(int i=1;i<=this.idx;i++)
            if (filename.equals(fileT.get(i)))
                throw new MyFileTableException("The file exit in the filetable!!!");
        return true;
    }
*/

}
