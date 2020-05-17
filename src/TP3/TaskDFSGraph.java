package TP3;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by efalcon
 */
public class TaskDFSGraph extends DirectedGraph<Tarea, Integer> {

    public List<Integer> dfs() {
        List<Integer> times;
        for (Vertex<Tarea, Integer> vertex : vertices) {
            List<List<Integer>> lists = dfsVisit(vertex, new ArrayList<>());
            return null;
        }
        return null;
    }

    // Guardar lista de vertices Ejemplo: 1,2 3,4,5
    // Guardar el tiempo
    // Deberia devolver una lista de listas?

    /**
     *
     * @param vertex
     * @param verticesRecorridos
     * @return un map, donde la key va a ser el tiempo total y el value la lista de vertices recorridoxs
     */
    public  List<List<Integer>> dfsVisit(Vertex<Tarea, Integer> vertex, List<List<Integer>> verticesRecorridos) {
        List<Integer> times = new ArrayList<>();
        boolean multiBranch = false;
        // Si no tiene ningun vertice agregado signfica que es el primer vertice y agregamos el vertexId
        if(verticesRecorridos.isEmpty()) {
            List<Integer> lista = new ArrayList<>();
            lista.add(vertex.getId());
            verticesRecorridos.add(lista);
        } else {
            // Sino buscamos la ultima lista de vertices agregada, y se lo agregamos a la lista
            verticesRecorridos.get(verticesRecorridos.size() - 1).add(vertex.getId());
        }
        List<Integer> aux = new ArrayList<>();
        for (Iterator<Arc<Integer>> it = vertex.getArcs(); it.hasNext(); ) {
            Arc<Integer> next = it.next();
//            times.add(next.getEtiqueta());
            Vertex<Tarea, Integer> adjacentVertex = findVertex(next.getVerticeDestino());
            // Si tiene mas de un adyacente agregamos el mismo elemento a la lista
            if(multiBranch) {
                verticesRecorridos.add(aux);
            } else {
                multiBranch = true;
                aux.addAll(verticesRecorridos.get(verticesRecorridos.size() - 1));
            }
            dfsVisit(adjacentVertex, verticesRecorridos);
        }
        times.add(vertex.getValue().getDuración());
        return verticesRecorridos;
    }
}
