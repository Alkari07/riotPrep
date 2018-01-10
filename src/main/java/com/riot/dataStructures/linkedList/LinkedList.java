package com.riot.dataStructures.linkedList;

public class LinkedList {
    Node head;

    public LinkedList (Node head) {
        this.head = head;
    }

    void appendToTail (int d) {
        Node end = new Node(d);
        Node n = this.head;
        while (n.next!=null) {
            n=n.next;
        }
        n.next=end;
    }

    void delete (int d) {
        if (this.head.data==d) {
            this.head=head.next;
            return;
        }

        Node n = this.head;
        while (n.next!=null) {
            if (n.next.data ==d) {
                n.next = n.next.next;
                return;
            }
            n=n.next;
        }
    }
}
