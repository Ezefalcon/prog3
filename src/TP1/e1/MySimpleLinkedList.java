package TP1.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MySimpleLinkedList {

    protected Node first;
    int size;

    public MySimpleLinkedList() {
        this.first = null;
        this.size = 0;
    }

    public void insertFront(Object o) {
        size++;
        Node tmp = new Node(o,null);
        tmp.setNext(this.first);
        this.first = tmp;
    }

    public Object extractFront() {
        Node temp = first;
        first = first.getNext();
        return temp;
    }

    public boolean isEmpty() {
        // TODO
        return !Objects.isNull(first);
    }

    public int size() {
        return this.size;
    }

//    private int getSize(Node next, int size) {
//        if(!Objects.isNull(next.getNext())) {
//            return getSize(next.getNext(), size++);
//        } else {
//            return size;
//        }
//    }

    public Iterator<Object> get(int index) {
        if(size > index) {
            return null;
        }
        Node currentNode = first;
        List<Object> nodes = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            nodes.add(currentNode.getInfo());
            currentNode = currentNode.getNext();
        }
        return nodes.iterator();
    }

}