package Model.Expressions;


//import Exceptions.MyControllerException;
import Exceptions.DivisionByZeroException;
import Exceptions.MyStmtException;
import Model.Dictionary.MyIDictionary;
import Model.Heap.MyIHeap;

public class ArithExp extends Exp
{
    private Exp e1;
    private Exp e2;
    private int op;

    public ArithExp(int op, Exp e1, Exp e2)
    {
        this.e1=e1;
        this.e2=e2;
        this.op=op;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> dict, MyIHeap<Integer> heap) throws MyStmtException, DivisionByZeroException{
        try
        {
            if (op == 1)
                return e1.eval(dict,heap) + e2.eval(dict,heap);
            if (op == 2)
                return e1.eval(dict,heap) - e2.eval(dict,heap);
            if (op == 3)
                return e1.eval(dict,heap) * e2.eval(dict,heap);
            if (op == 4)
            {
                if (e2.eval(dict,heap) == 0)
                    throw new DivisionByZeroException("Division by zero!!!");
                else
                    return e1.eval(dict,heap) / e2.eval(dict,heap);
            }
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
            return e1.toString()+"+"+ e2.toString();
        if (op == 2)
            return e1.toString()+"-"+ e2.toString();
        if (op == 3)
            return e1.toString()+"*"+ e2.toString();
        if (op == 4)
            return e1.toString()+"/"+ e2.toString();
        else
            return null;
    }
}

