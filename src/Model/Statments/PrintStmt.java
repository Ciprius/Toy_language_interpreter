package Model.Statments;


import Exceptions.MyControllerException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.Exp;
import Model.Heap.MyIHeap;
import Model.List.MyIList;
import Model.PrgState;

public class PrintStmt implements IStmt
{
    private Exp ex;


    public PrintStmt(Exp ex)
    {
        this.ex=ex;

    }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException {
        MyIList<Integer> list=state.getList();
        MyIDictionary<String,Integer> dict= state.getDict();
        MyIHeap<Integer> heap=state.getHeap();

        try {
            list.add(ex.eval(dict,heap));
            return null;
        }
        catch (MyStmtException e)
        {
            throw new MyControllerException(e);
        }
    }

    @Override
    public int check() {
        return 0;
    }
    @Override
    public int getexp(PrgState state) {
        try {
            return ex.eval(state.getDict(),state.getHeap());
        } catch (MyStmtException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String toString()
    {
        return "Print(" + ex.toString()+")";
    }
}
