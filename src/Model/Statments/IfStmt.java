package Model.Statments;


import Exceptions.MyControllerException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.Exp;
import Model.Heap.MyIHeap;
import Model.PrgState;
import Model.Stack.MyStack;

public class IfStmt implements IStmt
{
    private Exp ex;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp ex, IStmt thenS, IStmt elseS)
    {
        this.ex=ex;
        this.thenS=thenS;
        this.elseS=elseS;
    }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException {
        MyStack<IStmt> stk=state.getStk();
        MyIDictionary<String,Integer> dict=state.getDict();
        MyIHeap<Integer> heap=state.getHeap();

        try {
            if(ex.eval(dict,heap) > 0)
                stk.push(thenS);
            else
                stk.push(elseS);
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
            return ex.eval(state.getDict(),state.getHeap());
        } catch (MyStmtException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String toString()
    {
        return "IF("+ex.toString()+")THEN("+thenS.toString()+")ELSE("+elseS.toString()+")";
    }
}
