package TP3;

import java.util.Iterator;

/**
 * Created by efalcon
 */
public class Main {

    public static void main(String[] args) {
        TaskDFSGraph directedGraph = new TaskDFSGraph();
        // Vertices
        directedGraph.agregarVertice(0, new Tarea("Tarea 0", 0));
        directedGraph.agregarVertice(1, new Tarea("Tarea 1", 4));
        directedGraph.agregarVertice(2, new Tarea("Tarea 2", 18));
        directedGraph.agregarVertice(3, new Tarea("Tarea 3", 4));
        directedGraph.agregarVertice(4, new Tarea("Tarea 4", 13));
        directedGraph.agregarVertice(5, new Tarea("Tarea 5", 22));
        directedGraph.agregarVertice(6, new Tarea("Tarea 6", 18));
        directedGraph.agregarVertice(7, new Tarea("Tarea 7", 12));
        directedGraph.agregarVertice(8, new Tarea("Tarea 8", 3));
        directedGraph.agregarVertice(9, new Tarea("Tarea 9", 2));
        directedGraph.agregarVertice(10, new Tarea("Tarea 10", 3));
        directedGraph.agregarVertice(11, new Tarea("Tarea 11", 1));
        directedGraph.agregarVertice(12, new Tarea("Tarea 12", 5));
        // Arcos
        directedGraph.agregarArco(0,1,0);
        directedGraph.agregarArco(0,2,0);
        directedGraph.agregarArco(1,3,3);
        directedGraph.agregarArco(2,5,1);
        directedGraph.agregarArco(2,7,18);
        directedGraph.agregarArco(3,4,5);
        directedGraph.agregarArco(3,6,8);
        directedGraph.agregarArco(4,11,3);
        directedGraph.agregarArco(11,12,9);
        directedGraph.agregarArco(5,6,2);
        directedGraph.agregarArco(6,10,6);
        directedGraph.agregarArco(6,12,2);
        directedGraph.agregarArco(7,8,7);
        directedGraph.agregarArco(8,9,4);
        directedGraph.agregarArco(9,10,1);
        System.out.println("Secuencia de ejecucion critica = " + directedGraph.dfs());
        System.out.println("Contiene vertice 9 = " + directedGraph.contieneVertice(9));
        directedGraph.borrarVertice(9);
        System.out.println("Contiene vertice 9 despues de borrado = " + directedGraph.contieneVertice(9));
        System.out.println("Existe arco entre vertice 3 y 4 = " + directedGraph.existeArco(3,4));
        directedGraph.borrarArco(3,4);
        System.out.println("Existe arco entre vertice 3 y 4 despues de borrado = " + directedGraph.existeArco(3,4));
        System.out.println(directedGraph.obtenerArco(7,8));
        System.out.println("Cantidad de vertices del arco = " + directedGraph.cantidadVertices());
        System.out.println("Cantidad de arcos = " + directedGraph.cantidadArcos());
        System.out.print("Vertices -> ");
        printIterator(directedGraph.obtenerVertices());
        System.out.print("\nAdyacentes del vertice 6 -> " );
        printIterator(directedGraph.obtenerAdyacentes(6));
        System.out.println("\nArcos ->");
        printIterator(directedGraph.obtenerArcos());
        System.out.println("\nArcos del vertice 2 -> ");
        printIterator(directedGraph.obtenerArcos(2));
    }

    public static void printIterator(Iterator it) {
        it.forEachRemaining(x -> System.out.print(x + " "));
    }
}
