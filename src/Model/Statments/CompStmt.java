package Model.Statments;

import Model.PrgState;
import Model.Stack.MyStack;

public class CompStmt implements IStmt
{
    private IStmt st1;
    private IStmt st2;

    public CompStmt(IStmt st1, IStmt st2)
    {
        this.st1=st1;
        this.st2=st2;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyStack<IStmt> stk= state.getStk();
        stk.push(st2);
        stk.push(st1);
        return null;
    }

    @Override
    public int check() {
        return 0;
    }
    @Override
    public int getexp(PrgState state) { return 1000;
    }

    @Override
    public String toString()
    {
        return "("+ st1.toString() +" ; "+ st2.toString() + ")" ;
    }
}
