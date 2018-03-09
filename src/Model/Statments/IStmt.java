package Model.Statments;

import Exceptions.MyControllerException;
import Model.PrgState;

public interface IStmt
{
    public String toString();
    public PrgState execute(PrgState state) throws MyControllerException;
    public int check();
    public int getexp(PrgState state);
}
