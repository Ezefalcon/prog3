package TP1.e3;

import TP1.e1.MySimpleLinkedList;

public class Pila {

    MySimpleLinkedList list;
    int size;

    public Pila() {
        this.size = 0;
        list = new MySimpleLinkedList();
    }

    public void push(Object o) {
       list.insertFront(o);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Object pop() {
        return list.extractFront();
    }

    public Object top() {
        return list.peek();
    }

    public void reverse() {
        MySimpleLinkedList reversedList = new MySimpleLinkedList();
        while(!isEmpty()) {
            reversedList.insertFront(pop());
        }
        list = reversedList;
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
