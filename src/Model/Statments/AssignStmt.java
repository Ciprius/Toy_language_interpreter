package Model.Statments;

import Exceptions.DivisionByZeroException;
import Exceptions.MyControllerException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.Exp;
import Model.Heap.MyIHeap;
import Model.PrgState;

public class AssignStmt implements IStmt
{
    private String id;
    private Exp ex;

    public AssignStmt(String id,Exp ex)
    {
        this.ex=ex;
        this.id=id;
    }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException {
        //MyStack<IStmt> stk=state.getStk();
        MyIDictionary<String,Integer> dict= state.getDict();
        MyIHeap<Integer> heap=state.getHeap();

        try
        {
            int val=ex.eval(dict,heap);

            if(dict.isDefined(id) == 1)
                dict.update(id,val);
            else
                dict.add(id,val);
            return null;
        }
        catch (MyStmtException e)
        {
            throw new MyControllerException(e);
        }
        catch (DivisionByZeroException e)
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
        return id+"="+ ex.toString();
    }
}
