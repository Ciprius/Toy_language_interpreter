package Model.Statments;

import Exceptions.MyControllerException;
import Model.Dictionary.MyDictionary;
import Model.Dictionary.MyIDictionary;
import Model.PrgState;
import Model.Stack.MStack;
import Model.Stack.MyStack;

public class ForkStmt implements IStmt
{
    private IStmt stmt;

    public ForkStmt(IStmt stmt) {this.stmt=stmt; }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException
    {
        MyIDictionary<String,Integer> mydict= new MyDictionary<>();
        mydict.addall(state.getDict().getDict());
        MyStack<IStmt> stk=new MStack<>();
        PrgState status= new PrgState(stk,mydict,state.getList(),state.getFileT(),state.getHeap(),stmt,2);
        status.setId(status.getId()*10);
        return status;
    }

    @Override
    public String toString()
    {
        return "fork("+stmt.toString()+")";
    }

    @Override
    public int check() {return 0;}
    @Override
    public int getexp(PrgState state) {return 0;}
}
