package TP1.e1;

import java.util.Objects;

public class Node {

    private Integer info;
    private Node next;
    private Node previous;

    public Node(Integer info, Node next, Node previous) {
        this.info = info;
        this.next = next;
        this.previous = previous;
    }

    public Node(Integer info, Node next) {
        this.info = info;
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node(Integer info) {
        this.info = info;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getInfo() {
        return info;
    }

    public void setInfo(Integer info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(info, node.info) &&
                Objects.equals(next, node.next) &&
                Objects.equals(previous, node.previous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info, next, previous);
    }

    @Override
    public String toString() {
        return "Node{" +
                "info=" + info +
                ", next=" + next +
                '}';
    }
}
