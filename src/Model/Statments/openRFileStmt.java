package Model.Statments;

import Exceptions.MyControllerException;
import Model.Dictionary.MyIDictionary;
import Model.Filetable.MyIFileTable;
import Model.PrgState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFileStmt implements IStmt
{
    private String varName;
    private String filename;

    public openRFileStmt(String varName,String filename)
    {
        this.varName=varName;
        this.filename=filename;
    }

    @Override
    public PrgState execute(PrgState state) throws MyControllerException {
        MyIDictionary<String,Integer> dict=state.getDict();
        MyIFileTable<Tuple> fileT=state.getFileT();
        try
        {
                for (int i=1;i<=fileT.getkey();i++)
                {
                    Tuple tpl=fileT.get(i);
                    if (tpl !=null && filename.equals(tpl.getName()))
                        throw new MyControllerException("The file already exist!");
                }
                BufferedReader bf =new BufferedReader(new FileReader(this.filename));
                Tuple tpl= new Tuple(filename,bf);
                fileT.add(tpl);
                dict.add(this.varName,fileT.getkey());

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public int check() {
        return 0;
    }
    @Override
    public int getexp(PrgState state) {
        return 0;
    }


    @Override
    public String toString() {
        return "Opening:"+filename;
    }
}
