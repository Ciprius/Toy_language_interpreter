package Model.Statments;

import Exceptions.MyControllerException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.Exp;
import Model.Heap.MyIHeap;
import Model.PrgState;

public class NewStmt implements IStmt
{
    private String varName;
    private Exp exp;

    public NewStmt(String varName,Exp exp)
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
            int val=exp.eval(dict,heap);
            heap.add(val);
            int idx=heap.getKey();
            if (dict.isDefined(varName)==0)
                dict.add(varName,idx);
            else
                dict.update(varName,idx);

        } catch (MyStmtException e) {
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
        return "New("+ varName+","+exp.toString()+")";
    }
}
