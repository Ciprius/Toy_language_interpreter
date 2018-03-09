package Model.Expressions;

import Exceptions.MyDictionaryException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Heap.MyIHeap;

public class HeadReadExp extends Exp
{
    private String id;
    private int val;

    public HeadReadExp(String id)
    {
        this.id=id;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> dict, MyIHeap<Integer> heap) throws MyStmtException {
        try{
            val=dict.lookup(id);
            return heap.get(val);
        }
        catch (MyDictionaryException e)
        {
            throw new MyStmtException(e);
        }
    }

    @Override
    public String toString() {
        return "HeapRead("+id+")";
    }
}
