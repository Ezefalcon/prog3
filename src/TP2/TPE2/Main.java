package TP2.TPE2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by efalcon
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(21, 31, 35, 12, 16, 18, 6, 4, 33, 34, 38, 40, 1, 25, 17);
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
        System.out.println(tree);
        System.out.println("Delete 31 and printInOrder -> " + tree.delete(16));
        System.out.println("Delete 31 and printInOrder -> " + tree.delete(35));
        System.out.println("Delete 31 and printInOrder -> " + tree.delete(12));
        System.out.println("Delete 31 and printInOrder -> " + tree.delete(1));
        System.out.println(tree);

        tree.printInOrder();

        Tree treeRandomized = new Tree();
        treeRandomized.populateRandomizedTree();
        System.out.printf("\nPrint in order random tree -> ");
        treeRandomized.printInOrder();

    }
}
