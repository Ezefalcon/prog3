package TP1.e8;

import TP1.e1.Node;

import java.util.Objects;

public class DoubleLinkedList {
    private Node lastAdded;
    private Node start;
    private int size;

    public DoubleLinkedList() {
        size = 0;
    }

    public void push(Integer o) {
        size++;
        Node node = new Node(o, null);
        // Is the first item
        if(isEmpty()) {
            start = node;
            lastAdded = start;
        } else {
            lastAdded.setNext(node);
            node.setPrevious(lastAdded);
            start.setPrevious(node);
            lastAdded = node;
        }
    }

    public Object peekLast(){
        if(!isEmpty()) {
            return lastAdded.getInfo();
        }
        return null;
    }

    private boolean isEmpty() {
        return Objects.isNull(start);
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(55);
        System.out.println(list.peekLast());
    }
}
