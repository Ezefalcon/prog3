package TP2.TPE2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by efalcon
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(27, 38, 31, 9, 36, 19, 40, 20, 32, 18, 13, 6, 37, 26, 28, 21);
        Tree tree = new Tree();
        tree.addAll(integers);

        System.out.println("PreOrder");
        tree.printPreOrder();
        System.out.println("\nInOrder");
        tree.printInOrder();
        System.out.println("\nPostOrden");
        tree.printPostOrder();

        System.out.println("\nHas element 9 -> " + tree.hasElem(9));
        System.out.println("Has element 515 -> " + tree.hasElem(515));
        System.out.println("maxElem -> " + tree.getMaxElem());
        System.out.println("Height -> " + tree.getHeight());
        System.out.println("Frontier -> " + tree.getFrontier());
        System.out.println("Elements at level -> " + tree.getElemAtLevel(3));
        System.out.println("Longest branch -> " + tree.getLongestBranch());
        System.out.println("Delete 22 and printInOrder -> ");
        tree.delete(22);
        tree.printInOrder();

        Tree treeRandomized = new Tree();
        treeRandomized.populateRandomizedTree();
        System.out.printf("\nPrint in order random tree -> ");
        treeRandomized.printInOrder();

    }
}
