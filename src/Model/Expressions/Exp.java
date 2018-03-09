package Model.Expressions;

import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Heap.MyIHeap;

public abstract class Exp
{
    public abstract int eval(MyIDictionary<String,Integer> dict, MyIHeap<Integer> heap) throws MyStmtException;
    public abstract String toString();
}
