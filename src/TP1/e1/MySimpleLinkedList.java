package TP1.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MySimpleLinkedList implements Iterator{

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
        if(!Objects.isNull(first)) {
            Node temp = first;
            first = first.getNext();
            return temp.getInfo();
        }
        return null;
    }

    public boolean isEmpty() {
        return Objects.isNull(first);
    }

    public int size() {
        return this.size;
    }

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

    public int indexOf(Object o) {
        Node currentNode = first;
        for(int i = 0; i < size; i++) {
            if(currentNode.getInfo().equals(o)) {
                return size - 1 - i;
            }
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    public static void main(String[] args) {
        MySimpleLinkedList list = new MySimpleLinkedList();
        list.insertFront(1);
        list.insertFront(6);
        list.insertFront(7);
        list.insertFront(6);
        list.insertFront(3);
        list.insertFront(76);
        System.out.println(list.indexOf(76));
    }

    public Object peek() {
        return first.getInfo();
    }

    @Override
    public boolean hasNext() {
        return !Objects.isNull(first);
    }

    @Override
    public Object next() {
        return first;
    }
}