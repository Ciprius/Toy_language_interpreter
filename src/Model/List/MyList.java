package Model.List;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {

    private ArrayList<T> lists;

    public MyList()
    {
        this.lists =new ArrayList<>();
    }


    @Override
    public void add(T item)
    {
        lists.add(item);
    }

    @Override
    public void print() {
        for(T li:lists) {
            String str=li.toString();
            System.out.println(str);
        }
    }


    @Override
    public String toString()
    {
        String msg="";
        for(T elem:lists)
            msg+=elem.toString()+" "+'\n';
        return msg;
    }


    @Override
    public ArrayList<T> getlist() {
        return lists;
    }
}
