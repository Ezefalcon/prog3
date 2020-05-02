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
        return Objects.nonNull(findNode(value, root));
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

    /**
     * Complejidad: O(alturaMaxima)
     * Elimina un elemento del arbol
     * @param value
     * @return si lo elimino o no
     */
    @Override
    public boolean delete(int value) {
        if(Objects.isNull(root)) return false;
        if(root.getValue() == value) {
            if(Objects.isNull(root.getLeft()) && Objects.isNull(root.getRight())) { // Si es una hoja
                this.root = null;
            } else if(Objects.isNull(root.getLeft()) && Objects.nonNull(root.getRight())) { // Si tiene nodo derecho pero no izquierdo
                root = root.getRight();
            } else if(Objects.isNull(root.getRight()) && Objects.nonNull(root.getLeft())) { // Si tiene nodo izquierdo pero no derecho
                root = root.getLeft();
            } else {
                TreeNode currentNode = root.getRight();
                // Busca el nodo anterior al mas izquierdo en el nodo derecho
                while(Objects.nonNull(currentNode.getLeft()) && Objects.nonNull(currentNode.getLeft().getLeft())) {
                    currentNode = currentNode.getLeft();
                }
                TreeNode aux = currentNode;
                if(Objects.nonNull(currentNode.getLeft())) {
                    aux = currentNode.getLeft();
                }
                if(Objects.nonNull(aux.getRight())) {
                    currentNode.setLeft(aux.getRight());
                } else {
                    currentNode.setLeft(null);
                }
                aux.setLeft(root.getLeft());
                aux.setRight(root.getRight());
                root = aux;

            }
            return true;
        }
        return delete(value, root);
    }

    private boolean delete(int value, TreeNode node) {
        // Si llego a esta instancia es porque el nodo propio no contiene el valor que se esta buscando
        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        if(Objects.nonNull(left) && left.getValue() == value) {
            if(Objects.isNull(left.getLeft()) && Objects.isNull(left.getRight())) { //Si es una hoja
                node.setLeft(null);
            } else if(Objects.isNull(left.getLeft()) && Objects.nonNull(left.getRight())) { // Si tiene nodo derecho pero no izquierdo
                node.setLeft(left.getRight());
            } else if(Objects.isNull(left.getRight()) && Objects.nonNull(left.getLeft())) { // Si tiene nodo izquierdo pero no derecho
                node.setLeft(left.getLeft());
            } else { // tiene ambos nodos
                TreeNode currentNode = left.getRight();
                // Busca el nodo anterior al mas izquierdo en el nodo derecho
                while(Objects.nonNull(currentNode.getLeft()) && Objects.nonNull(currentNode.getLeft().getLeft())) {
                    currentNode = currentNode.getLeft();
                }
                TreeNode aux = currentNode;
                if(Objects.nonNull(currentNode.getLeft())) {
                    aux = currentNode.getLeft();
                }
                if(Objects.nonNull(aux.getRight())) {
                    currentNode.setLeft(aux.getRight());
                } else {
                    currentNode.setLeft(null);
                }
                aux.setLeft(left.getLeft());
                aux.setRight(left.getRight());
                node.setLeft(aux);
            }
            return true;
        } else if(Objects.nonNull(node.getRight()) && node.getRight().getValue() == value) {
            if(Objects.isNull(right.getLeft()) && Objects.isNull(right.getRight())) { //Si es una hoja
                node.setRight(null);
            } else if(Objects.isNull(right.getLeft()) && Objects.nonNull(right.getRight())) { // Si tiene nodo derecho pero no izquierdo
                node.setRight(right.getRight());
            } else if(Objects.isNull(right.getRight()) && Objects.nonNull(right.getLeft())) { // Si tiene nodo izquierdo pero no derecho
                node.setRight(right.getLeft());
            } else {
                TreeNode currentNode = right.getRight();
                // Busca el nodo anterior al mas izquierdo en el nodo derecho
                while(Objects.nonNull(currentNode.getLeft()) && Objects.nonNull(currentNode.getLeft().getLeft())) {
                    currentNode = currentNode.getLeft();
                }
                TreeNode aux = currentNode;
                if(Objects.nonNull(currentNode.getLeft())) {
                    aux = currentNode.getLeft();
                }
                if(Objects.nonNull(aux.getRight())) {
                    currentNode.setLeft(aux.getRight());
                } else {
                    currentNode.setLeft(null);
                }
                aux.setLeft(right.getLeft());
                aux.setRight(right.getRight());
                node.setRight(aux);
            }
            return true;
        }
        boolean isDeleted = false;
        if(value < node.getValue() && Objects.nonNull(node.getLeft())) {
            isDeleted = delete(value, node.getLeft());
        }
        if(value > node.getValue() && Objects.nonNull(node.getRight()) && !isDeleted) {
            isDeleted = delete(value, node.getRight());
        }
        return isDeleted;
    }

    /**
     * Complejidad: O(n)
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
     * @return La altura maxima del arbol
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
        if(Objects.nonNull(node.getRight())) {
            rightHeight = getHeight(node.getRight());
        }
        if(Objects.nonNull(node.getLeft())) {
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
     * @return una lista con los valores
     */
    @Override
    public List<Integer> getLongestBranch() {
        if(Objects.isNull(this.root))
            return new ArrayList<>();
        else {
            List<Integer> longestBranch = getLongestBranch(this.root);
            Collections.reverse(longestBranch);
            return longestBranch;
        }
    }

    private List<Integer> getLongestBranch(TreeNode node) {
        if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
            return Arrays.asList(node.getValue());
        } else {
            ArrayList<Integer> rightBranch = new ArrayList<>();
            ArrayList<Integer> leftBranch = new ArrayList<>();

            if (Objects.nonNull(node.getRight()))
                rightBranch.addAll(getLongestBranch(node.getRight()));
            if (Objects.nonNull(node.getLeft()))
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
            if (Objects.nonNull(node.getLeft()))
                nodes.addAll(getFrontier(node.getLeft()));
            if (Objects.nonNull(node.getRight()))
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
        while(Objects.nonNull(currentNode.getRight())) {
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
        if (Objects.nonNull(node.getRight()))
            values.addAll(getElemAtLevel(node.getRight(), currentLevel+1, level));
        if (Objects.nonNull(node.getLeft()))
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
        List<Integer> list = new ArrayList<>();
        list.addAll(randoms);
        Collections.shuffle(list);
        addAll(list);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
