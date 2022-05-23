package com.cyberpanterra.book_2.interfaces;

/* 
    The creator of the Action interface is Asadjon Xusanjonov
    Created on 17:30, 28.03.2022
*/

public class Action{

    public interface IRAction<TInput, TResult> {
        TResult call(TInput target);
    }
    public interface IAction<TInput> {
        void call(TInput target);
    }
}
