package Model.Expressions;

import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Heap.MyIHeap;

public class BooleanExp extends Exp
{
    private Exp e1;
    private Exp e2;
    private int op;

    public BooleanExp(Exp e1, Exp e2 , int op)
    {
        this.e1=e1;
        this.e2=e2;
        this.op=op;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> dict, MyIHeap<Integer> heap) throws MyStmtException
    {
        try {
            if (op == 1){
                if (e1.eval(dict, heap)<e2.eval(dict, heap))
                    return 1;
                else
                    return 0;}
            if (op == 2){
                if (e1.eval(dict, heap)<=e2.eval(dict, heap))
                    return 1;
                else
                    return 0;}
            if (op == 3){
                if (e1.eval(dict, heap)==e2.eval(dict, heap))
                    return 1;
                else
                    return 0;}
            if (op == 4){
                if (e1.eval(dict, heap)!=e2.eval(dict, heap))
                    return 1;
                else
                    return 0;}
            if (op == 5){
                if (e1.eval(dict, heap)>e2.eval(dict, heap))
                    return 1;
                else
                    return 0;}
            if (op == 6){
                if (e1.eval(dict, heap)>=e2.eval(dict, heap))
                    return 1;
                else
                    return 0;}
            else
                throw new MyStmtException("The operand does not exist!!");
        }
        catch (MyStmtException e)
        {
            throw new MyStmtException(e);
        }
    }

    @Override
    public String toString() {
        if (op == 1)
            return e1.toString()+"<"+ e2.toString();
        if (op == 2)
            return e1.toString()+"<="+ e2.toString();
        if (op == 3)
            return e1.toString()+"=="+ e2.toString();
        if (op == 4)
            return e1.toString()+"!="+ e2.toString();
        if (op == 5)
            return e1.toString()+">"+ e2.toString();
        if (op == 6)
            return e1.toString()+">="+ e2.toString();
        else
            return null;
    }
}
