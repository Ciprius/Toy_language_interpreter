package Model;

public class SymTable
{
    private String varN;
    private int valS;


    public SymTable(String varN, int valS)
    {
        this.valS=valS;
        this.varN=varN;
    }
    public String getVarN() { return varN;}
    public int getValS() { return valS;}

}
