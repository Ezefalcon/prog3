package TP1.e1;

import java.util.Iterator;

public class MyIterator implements Iterator<Integer> {

    private Node currentNode;

    public MyIterator(Node currentNode) {
        this.currentNode = currentNode;
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public Integer next() {
        Integer info = currentNode.getInfo();
        currentNode = currentNode.getNext();
        return info;
    }
}
