package TP3;

import java.util.Iterator;
import java.util.List;

import static TP3.Color.*;

/**
 * Created by efalcon
 */
public class DepthFieldSearch {

    public static void main(String[] args) {
        DirectedGraph<Tarea, Integer> directedGraph = new DirectedGraph<>();
        directedGraph.agregarVertice(12);
        directedGraph.agregarVertice(15);
        directedGraph.agregarVertice(17);
        directedGraph.agregarArco(12,15, new Tarea("ASD","ASD",12));
        directedGraph.borrarVertice(17);
        System.out.println(directedGraph.existeArco(12,15));
    }

    public static List<Integer> dfs(Graph<Tarea, Integer> graph) {
        Iterator<Vertex<Tarea, Integer>> iterator = graph.obtenerVertices();
        for (Iterator<Vertex<Tarea, Integer>> it = iterator; it.hasNext(); ) {
            it.next().setColor(BLANCO);
        }
        int time = 0;
        for (Iterator<Vertex<Tarea, Integer>> it = iterator; it.hasNext(); ) {
            Vertex<Tarea, Integer> next = it.next();
            if(next.getColor() == BLANCO) {
                dfsVisit(next, time);
            }
        }
    }

    private static void dfsVisit(Vertex<Tarea> vertex, int time) {
        vertex.setColor(AMARILLO);
        time += vertex.getValue().getDuración();
        vertex.getArcs

    }
}
