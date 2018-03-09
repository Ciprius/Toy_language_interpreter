package Model.Stack;

import Exceptions.MyStackException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MStack<T> implements MyStack<T>
{
    private Stack<T> stk;

    public MStack()
    {
        this.stk= new Stack<>();
    }

    @Override
    public Stack<T> getSkt() {
        return stk;
    }

    public void isEmpty() throws MyStackException {
        if (stk.isEmpty())
            throw new MyStackException("The stack is empty!!");
    }

    @Override
    public void setStack(Stack<T> st)
    {
        this.stk=st;
    }

    @Override
    public T pop()
    {
        return this.stk.pop();
    }

    @Override
    public void push(T elem)
    {
        this.stk.push(elem);
    }

    @Override
    public String toString()
    {
        ArrayList<T> list= new ArrayList<>();
        for (T ele:stk)
        {
            list.add(ele);
        }
        Collections.reverse(list);

        String msg="";
        for (T el:list)
            msg=msg + el + '\n';

        return msg;
    }
}
