package TP2.TPE2;

import java.util.*;

/**
 * Created by efalcon
 */
public class Tree implements ITree {
    /** Root of tree. */
    private TreeNode root;

    /** Constructor. */
    public Tree() {
        this.root = null;
    }

    /**
     * Complejidad: O(1)
     * @return valor de la raiz
     */
    @Override
    public int getRoot() {
        return root.getValue();
    }

    /**
     * Complejidad: O(n) donde n serian todas las hojas del arbol
     * @return si tiene el elemento o no
     */
    @Override
    public boolean hasElem(int value) {
        return !Objects.isNull(findNode(value, root));
    }

    /**
     * Complejidad: O(1)
     * @return si esta vacio
     */
    @Override
    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    /**
     * Complejidad: O(alturaMaxima)
     * Inserta un elemento en el arbol ordenado
     * @param value
     */
    @Override
    public void insert(int value) {
        if (this.root == null)
            this.root = new TreeNode(value);
        else
            this.insert(this.root,value);
    }

    private void insert(TreeNode actual, int value) {
        if (actual.getValue() > value) {
            if (actual.getLeft() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setLeft(temp);
            } else {
                insert(actual.getLeft(),value);
            }
        } else {
            if (actual.getRight() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setRight(temp);
            } else {
                insert(actual.getRight(),value);
            }
        }
    }

    @Override
    public boolean delete(int elem) {
        return false;
    }

    private boolean delete(int value, TreeNode treeNode) {
        return false;
    }

    /**
     * Encuentra un nodo
     * @return Node o null si no se encontro
     */
    private TreeNode findNode(int value, TreeNode treeNode) {
        if(value > treeNode.getValue()) {
            if(Objects.isNull(treeNode.getRight())) {
                return null;
            } else {
                return findNode(value, treeNode.getRight());
            }
        } else if(value < treeNode.getValue()) {
            if(Objects.isNull(treeNode.getLeft())) {
                return null;
            } else {
                return findNode(value, treeNode.getLeft());
            }
        } else {
            return treeNode;
        }
    }

    /**
     * Complejidad: O(n)
     * @return La altura del arbol
     */
    @Override
    public int getHeight() {
        if(Objects.isNull(root)) return 0;
        return getHeight(root);
    }

    private int getHeight(TreeNode node) {
        if(Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        if(!Objects.isNull(node.getRight())) {
            rightHeight = getHeight(node.getRight());
        }
        if(!Objects.isNull(node.getLeft())) {
            leftHeight = getHeight(node.getLeft());
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Complejidad: O(n)
     * Imprime el arbol en postOrden
     */
    @Override
    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(TreeNode node) {
        if(Objects.isNull(node)) return;
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    /**
     * Complejidad: O(n)
     * Imprime el arbol en preOrden
     */
    @Override
    public void printPreOrder() {
        printPreOrder(root);
    }

    public void printPreOrder(TreeNode node) {
        if(Objects.isNull(node)) return;
        System.out.print(node.getValue() + " ");
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }

    /**
     * Complejidad: O(n)
     * Imprime el arbol en orden
     */
    @Override
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(TreeNode node) {
        if(Objects.isNull(node)) return;
        printInOrder(node.getLeft());
        System.out.print(node.getValue() + " ");
        printInOrder(node.getRight());
    }

    /**
     * Complejidad: O(n)
     * Encuentra la rama mas larga del arbol
     * @return
     */
    @Override
    public List<Integer> getLongestBranch() {
        if(Objects.isNull(this.root))
            return new ArrayList<>();
        else return getLongestBranch(this.root);
    }

    private List<Integer> getLongestBranch(TreeNode node) {
        if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
            return Arrays.asList(node.getValue());
        } else {
            ArrayList<Integer> rightBranch = new ArrayList<>();
            ArrayList<Integer> leftBranch = new ArrayList<>();

            if (!Objects.isNull(node.getRight()))
                rightBranch.addAll(getLongestBranch(node.getRight()));
            if (!Objects.isNull(node.getLeft()))
                leftBranch.addAll((getLongestBranch(node.getLeft())));

            if (leftBranch.size() > rightBranch.size()) {
                leftBranch.add(node.getValue());
                return leftBranch;
            } else {
                rightBranch.add(node.getValue());
                return rightBranch;
            }
        }
    }

    /**
     * Complejidad: O(n)
     * Busca los elementos que no tengan nodos hijos
     * @return lista de elementos que no tienen hijos
     */
    @Override
    public List<Integer> getFrontier() {
        if(Objects.isNull(root)) return new ArrayList<>();
        return getFrontier(root);
    }

    private List<Integer> getFrontier(TreeNode node) {
        if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
            return Arrays.asList(node.getValue());
        } else {
            ArrayList<Integer> nodes = new ArrayList<>();
            if (!Objects.isNull(node.getLeft()))
                nodes.addAll(getFrontier(node.getLeft()));
            if (!Objects.isNull(node.getRight()))
                nodes.addAll(getFrontier(node.getRight()));
            return nodes;
        }
    }

    /**
     * Complejidad: O(n)
     * Busca la hoja mas a la derecha posible
     * @return el maximo valor en el arbol || 0
     */
    @Override
    public int getMaxElem() {
        if(Objects.isNull(this.root)) return 0;
        TreeNode currentNode = root;
        while(!Objects.isNull(currentNode.getRight())) {
            currentNode = currentNode.getRight();
        }
        return currentNode.getValue();
    }

    /**
     * Complejidad: O(n)
     * @param i nivel del arbol
     * @return elementos en determinado nivel
     */
    @Override
    public List<Integer> getElemAtLevel(int i) {
        if(Objects.isNull(root)) return new ArrayList<>();
        return getElemAtLevel(root, 0, i);
    }

    private List<Integer> getElemAtLevel(TreeNode node, int currentLevel, int level) {
        if(level == currentLevel) {
            return Arrays.asList(node.getValue());
        }
        List<Integer> values = new ArrayList<>();
        if (!Objects.isNull(node.getRight()))
            values.addAll(getElemAtLevel(node.getRight(), currentLevel+1, level));
        if (!Objects.isNull(node.getLeft()))
            values.addAll(getElemAtLevel(node.getLeft(), currentLevel+1, level));
        return values;
    }

    public void addAll(Collection<Integer> values) {
        values.forEach(this::insert);
    }

    /**
     * Populates tree with random numbers
     */
    public void populateRandomizedTree() {
        Set<Integer> randoms = new HashSet<>();
        while(randoms.size() <= 15) {
            randoms.add((int) (Math.random() * 40) + 1);
        }
        addAll(randoms);
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(6,15,18,3,7,2,4,17,13,9,20);
        Tree tree = new Tree();
        tree.addAll(integers);
        System.out.println(tree.hasElem(3));
        System.out.println(tree.hasElem(9));
        System.out.println(tree.hasElem(333));
        System.out.println("PreOrder");
        tree.printPreOrder();
        System.out.println("\nInOrder");
        tree.printInOrder();
        System.out.println("\nPostOrden");
        tree.printPostOrder();

        System.out.println("\nmaxElem = " + tree.getMaxElem());

        System.out.println("Frontier " + tree.getFrontier());
        System.out.println("Elements at level" + tree.getElemAtLevel(3));
        Tree treeRandomized = new Tree();
        treeRandomized.populateRandomizedTree();
        treeRandomized.printInOrder();

    }
}
