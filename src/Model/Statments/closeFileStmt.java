package Model.Statments;

import Exceptions.MyControllerException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.Exp;
import Model.Filetable.MyIFileTable;
import Model.Heap.MyIHeap;
import Model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class closeFileStmt implements IStmt
{
    private Exp exp;

    public closeFileStmt(Exp exp)
    {
        this.exp=exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException
    {
        MyIDictionary<String,Integer> dict=state.getDict();
        MyIFileTable<Tuple> Tpl=state.getFileT();
        MyIHeap<Integer> heap=state.getHeap();

        try
        {
            int val=exp.eval(dict,heap);
            Tuple tpl=Tpl.get(val);
            if (tpl !=null)
            {
                BufferedReader bf=tpl.getBf();
                bf.close();
                Tpl.remove(val);
            }
            else
                throw new MyControllerException("The file does not exist!!");

        } catch (MyStmtException e) {
            throw new MyControllerException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
    public int check() {
        return 0;
    }

    @Override
    public String toString() {
        return "Closing the file";
    }
}
