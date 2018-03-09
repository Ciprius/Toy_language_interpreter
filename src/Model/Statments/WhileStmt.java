package Model.Statments;

import Exceptions.MyControllerException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.Exp;
import Model.Heap.MyIHeap;
import Model.PrgState;
import Model.Stack.MyStack;

public class WhileStmt implements IStmt
{
    private Exp exp;
    private IStmt st1;

    public WhileStmt(Exp exp, IStmt st1)
    {
        this.exp=exp;
        this.st1=st1;
    }


    @Override
    public PrgState execute(PrgState state) throws MyControllerException
    {
        MyIHeap<Integer> heap=state.getHeap();
        MyIDictionary<String,Integer> dict=state.getDict();
        MyStack<IStmt> stk=state.getStk();

        try {
            if(exp.eval(dict,heap) != 0)
            {
                stk.push(st1);
            }
            return null;
        } catch (MyStmtException e) {
            throw new MyControllerException(e);
        }
    }

    @Override
    public int check() {
        return 1;
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
        return "While("+exp.toString()+")"+"{"+st1.toString()+"}";
    }
}
