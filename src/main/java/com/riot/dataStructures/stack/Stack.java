package com.riot.dataStructures.stack;

public class Stack  <T> {
    private StackNode<T> top;

    public T pop() {
        if (top ==null) {
            //throw empty stack exception
        }
        T item = top.getData();
        top=top.getNext();
        return item;
    }

    public void push(T data) {
        StackNode<T> newNode = new StackNode<T>(data);
        newNode.setNext(top);
        this.top=newNode;
    }

    public T peek () {
        //ALWAYS CHECK FOR THE EDGE CONDITION
        if (top==null) {
            //throw empty stack exception
        }
        return this.top.getData();
    }

    public Boolean isEmpty() {
        return top==null;
    }

}
