package com.riot.dataStructures.queue;

public class QueueNode<T> {
    private T data;
    private QueueNode next;

    public QueueNode (T data) {
        this.data=data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }
}
