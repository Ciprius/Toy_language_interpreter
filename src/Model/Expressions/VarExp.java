package Model.Expressions;

import Exceptions.MyDictionaryException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Heap.MyIHeap;

public class VarExp extends Exp
{
    private String id;
    private int val;

    public  VarExp(String id)
    {
        this.id=id;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> dict, MyIHeap<Integer> heap) throws MyStmtException {
        try{
            val=dict.lookup(id);
            return val;
        }
        catch (MyDictionaryException e)
        {
            throw new MyStmtException(e);
        }
    }

    @Override
    public String toString() {
        return id;
    }
}
