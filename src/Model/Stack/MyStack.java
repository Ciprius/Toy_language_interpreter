package Model.Stack;

import Exceptions.MyStackException;

import java.util.Stack;

public interface MyStack<T>
{
    public T pop();
    public void push(T elem);
    public Stack<T> getSkt();
    public void isEmpty() throws MyStackException;
    public void setStack(Stack<T> st);
    public String toString();
}
