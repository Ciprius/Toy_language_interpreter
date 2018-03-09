package Model;

import Exceptions.*;
import Model.Dictionary.MyIDictionary;
import Model.Filetable.MyIFileTable;
import Model.Heap.MyIHeap;
import Model.List.MyIList;
import Model.Stack.MyStack;
import Model.Statments.IStmt;
import Model.Statments.Tuple;

public class PrgState
{
    private MyStack<IStmt> stk;
    private MyIDictionary<String,Integer> dict;
    private MyIList<Integer> list;
    private MyIFileTable<Tuple> fileT;
    private MyIHeap<Integer> heap;
    private int id;

    public PrgState(MyStack<IStmt> stk, MyIDictionary<String,Integer> dict, MyIList<Integer> list, MyIFileTable<Tuple> fileT,
                    MyIHeap<Integer> heap, IStmt state, int id)
    {
        this.stk=stk;
        this.dict=dict;
        this.list=list;
        this.fileT=fileT;
        this.heap=heap;
        this.id=id;
        stk.push(state);
    }

    public boolean isNotCompleted()
    {
        try {
            stk.isEmpty();
            return true;
        } catch (MyStackException e) {
            return false;
        }
    }

    public PrgState oneStep()
    {
        try
        { stk.isEmpty(); }
        catch (MyStackException e) { System.out.println(e);}

        try
        {
            IStmt prog=stk.pop();
            System.out.println(prog.toString());
            if (prog.check() == 1 && prog.getexp(this)!=0)
                stk.push(prog);
            return prog.execute(this);
        }
        catch (MyControllerException e) { System.out.println(e); return null;}

    }

    public int getId() {return id;}
    public void setId(int id) {this.id=id;}

    public MyStack<IStmt> getStk() { return stk; }
    public void setStk(MyStack<IStmt> stk) { this.stk = stk; }

    public MyIDictionary<String, Integer> getDict()
    {
        return dict;
    }
    public void setDict(MyIDictionary<String, Integer> dict) {
        this.dict = dict;
    }

    public MyIList<Integer> getList() {
        return list;
    }
    public void setList(MyIList<Integer> list) {
        this.list = list;
    }

    public MyIFileTable<Tuple> getFileT() {
        return fileT;
    }
    public void setFileT(MyIFileTable<Tuple> fileT) {
        this.fileT = fileT;
    }

    public MyIHeap<Integer> getHeap() {return heap; }
    public void setHeap(MyIHeap<Integer> heap) {this.heap = heap; }

    public String getIDString()
    { return ""+id;}
}
