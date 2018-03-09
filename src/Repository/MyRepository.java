package Repository;

import Model.PrgState;

import java.io.PrintWriter;
import java.util.List;

public interface MyRepository
{
   public List<PrgState> getPrgList();
   public void setPrgList(List<PrgState> prog);
   public void LogProgState(PrgState prog);
   public void CloseFile();
   public void SetPrint(PrintWriter fw);
   public PrintWriter GetPrint();
   public String GetFileName();
   public void SpecialClose();
   //public void add(PrgState prog);
}
