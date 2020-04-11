package TP1.e3;

import TP1.e1.Node;

import java.util.Objects;

public class Pila {

    protected Node first;
    protected Node last;
    int size;

    public Pila() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void push(Object o) {
        size++;
        Node tmp = new Node(o,null);
        tmp.setNext(this.first);
        if(isEmpty()) {
            last = tmp;
        }
        this.first = tmp;
    }

    public boolean isEmpty() {
        return !Objects.isNull(first);
    }

    public Object pop() {
        if(!Objects.isNull(this.first)) {
            Object tmp = this.first.getInfo();
            this.first = this.first.getNext();
            return tmp;
        }
        return null;
    }

    public Object top() {
        if(!Objects.isNull(this.first)) {
            return this.first.getInfo();
        }
        return null;
    }

    private void pushLast(Node o) {
        Node tmp = new Node(o,null);
        if(isEmpty()) {
           first = tmp;
        } else {
            this.last.setNext(tmp);
        }
        last = tmp;
    }

    public void reverse() {
        Node tempFirst = this.last;
        Node n = this.first;
        while (n != null) {
            n = n.getNext();
            tempFirst.setNext(n);
        }
        this.first = tempFirst;
    }

    public static void main(String[] args) {
        Pila p = new Pila();
        p.push(12);
        p.push(13);
        p.push(14);
        p.reverse();
        for(int i = 0; i<3;i++) {
            System.out.println(p.pop());
        }
    }

}
