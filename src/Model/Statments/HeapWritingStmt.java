package Model.Statments;

import Exceptions.MyControllerException;
import Exceptions.MyDictionaryException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.Exp;
import Model.Heap.MyIHeap;
import Model.PrgState;

public class HeapWritingStmt implements IStmt
{
    private String varName;
    private Exp exp;

    public HeapWritingStmt(String varName,Exp exp)
    {
        this.exp=exp;
        this.varName=varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException
    {
        MyIHeap<Integer> heap=state.getHeap();
        MyIDictionary<String,Integer> dict=state.getDict();

        try {
            int id=dict.lookup(varName);
            int val=exp.eval(dict,heap);
            if (heap.get(id) == null)
                throw new MyControllerException("The value does not exist!");
            else
                heap.update(id,val);

        } catch (MyStmtException e) {
            throw new MyControllerException(e);
        } catch (MyDictionaryException e) {
            throw new MyControllerException(e);
        }

        return null;
    }

    @Override
    public int check() {
        return 0;
    }

    @Override
    public int getexp(PrgState state) {
        try {
            return exp.eval(state.getDict(),state.getHeap());
        } catch (MyStmtException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public String toString()
    {
        return "WriteHeap("+varName+","+exp.toString()+")";
    }
}
