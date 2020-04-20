package TP1.e1;

import java.util.*;

public class MySimpleLinkedList implements Iterable<Integer> {

    protected Node first;
    protected Node lastAdded;
    int size;

    public MySimpleLinkedList() {
        this.first = null;
        this.size = 0;
    }

    public Integer add(Integer info) {
        size++;
        Node node = new Node(info);
        if (!isEmpty()) {
            lastAdded.setNext(node);
        } else {
            this.first = node;
        }
        lastAdded = node;
        return node.getInfo();
    }

    public void addAll(List<Integer> list) {
        for(Integer i : list) {
            add(i);
        }
    }

    public void insertFront(Integer o) {
        Node tmp = new Node(o,this.first);
        size++;
        this.first = tmp;
    }

    public Integer extractFront() {
        if(!Objects.isNull(first)) {
            Node temp = first;
            first = first.getNext();
            size--;
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

    public List<MySimpleLinkedList> getSubSequences() {
        List<MySimpleLinkedList> list = new ArrayList<>();
        Node currentNode = first;
        int currentNumber = Integer.MIN_VALUE;
        MySimpleLinkedList myList = new MySimpleLinkedList();
        while(currentNode != null) {
            if(currentNode.getInfo() > currentNumber) {
                myList.add(currentNode.getInfo());
            } else {
                if (myList.size() > 1) {
                    list.add(myList);
                }
                myList = new MySimpleLinkedList();
                myList.add(currentNode.getInfo());
            }
            currentNumber = currentNode.getInfo();
            currentNode = currentNode.getNext();
        }
        if (myList.size() > 1) {
            list.add(myList);
        }
        return list;
    }

    public static void main(String[] args) {
        MySimpleLinkedList list = new MySimpleLinkedList();
        MySimpleLinkedList list1 = new MySimpleLinkedList();
        MySimpleLinkedList list2 = new MySimpleLinkedList();
        list.addAll(Arrays.asList(1, 2, 2));
        list1.addAll(Arrays.asList(3, 5, 2, 7, 19, 14, 28));
        list2.addAll(Arrays.asList(3, 5, 2, 2, 7, 19, 14, 28));
        System.out.println(list.getSubSequences());
        System.out.println(list1.getSubSequences());
        System.out.println(list2.getSubSequences());
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator(first);
    }

    @Override
    public String toString() {
        return "MySimpleLinkedList{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }
}