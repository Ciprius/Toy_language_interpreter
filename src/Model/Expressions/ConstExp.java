package Model.Expressions;

import Model.Dictionary.MyIDictionary;
import Model.Heap.MyIHeap;

public class ConstExp extends Exp
{
    private int value;

    public ConstExp(int value)
    {
        this.value=value;
    }

    @Override
    public int eval(MyIDictionary<String,Integer> dict, MyIHeap<Integer> heap) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
