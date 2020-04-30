package TP2.TPE2;

import java.util.List;

public interface ITree {

    int getRoot();

    boolean hasElem(int value);

    boolean isEmpty();

    void insert(int value);

    boolean delete(int value);

    int getHeight();

    void printPostOrder();

    void printPreOrder();

    void printInOrder();

    List<Integer> getLongestBranch();

    List<Integer> getFrontier();

    int getMaxElem();

    List<Integer> getElemAtLevel(int i);
}
