package Repository;

import Model.PrgState;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements MyRepository
{
    private List<PrgState> list;
    private String filename;
    private PrintWriter fw;


    public Repository(PrgState prog, String filename) throws IOException {
        list= new ArrayList<>();
        list.add(prog);
        this.filename=filename;
        try {
            fw=new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PrgState> getPrgList()
    {
        return list;
    }

    @Override
    public void setPrgList(List<PrgState> prog) {
        list=prog;
    }

    @Override
    public void LogProgState(PrgState prog)
    {
        fw.println("==================================");
        fw.println("The prog who write into file: "+prog.getId()+"\n");
        fw.println("Execution Stack \n");
        fw.println(prog.getStk().toString());
        fw.println("Symbol Table \n");
        fw.println(prog.getDict().toString());
        fw.println("Output Table \n");
        fw.println(prog.getList().toString());
        fw.println("FileTable \n");
        fw.println(prog.getFileT().toString());
        fw.println("Heap Management \n");
        fw.println(prog.getHeap().toString());
    }

    @Override
    public void CloseFile() {
        fw.close();
    }

    @Override
    public void SetPrint(PrintWriter fw) {
        this.fw=fw;
    }

    @Override
    public PrintWriter GetPrint() {
        return fw;
    }

    @Override
    public String GetFileName() {
        return this.filename;
    }

    @Override
    public void SpecialClose() {
        fw.print("");
        fw.close();
    }


}
