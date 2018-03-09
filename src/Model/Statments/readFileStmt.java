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

public class readFileStmt implements IStmt
{
    private Exp exp;
    private String name;

    public readFileStmt(Exp exp, String name)
    {
        this.exp=exp;
        this.name=name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException {
        MyIDictionary<String,Integer> dict=state.getDict();
        MyIFileTable<Tuple> Tpl=state.getFileT();
        MyIHeap<Integer> heap=state.getHeap();

        try
        {
            int val=exp.eval(dict,heap);
            Tuple tpl=Tpl.get(val);
            if (tpl!= null)
            {
                BufferedReader bf=tpl.getBf();
                String st=bf.readLine();
                if (st != null )
                    if (dict.isDefined(name)==0)
                        dict.add(name,Integer.parseInt(st));
                    else
                        dict.add(name,Integer.parseInt(st));
                else
                    dict.add(name,0);
            }
            else
                throw new MyControllerException("The file does not exit!!");
        }
        catch (MyStmtException e) {
            throw new MyControllerException(e);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return null ;
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
        return "Reading from :" + this.exp.toString();
    }
}
