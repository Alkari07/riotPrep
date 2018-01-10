package com.riot.dataStructures.queue;

public class SampleQueue<T> {
    private QueueNode<T> head;
    private QueueNode<T> tail;

    public void add(T data) {
        QueueNode<T> newNode = new QueueNode<T> (data);
        if (tail!=null) {
            tail.setNext(newNode);
        }
        tail=newNode;
        if (head==null) {
            head=tail;
        }

    }

    public T remove() {
        if (head==null) {
            //empty queue;
        }
        T result = head.getData();
        head=head.getNext();
        if (head==null) {
            tail=null;
        }
        return result;
    }

    public T peek() {
        if (head==null) {
            //throw empty queue exception
        }
        return head.getData();
    }

    public Boolean isEmpty() {
        return head==null;
    }
}
