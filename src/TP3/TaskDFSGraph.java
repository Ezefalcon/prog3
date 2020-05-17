package TP3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by efalcon
 */
public class TaskDFSGraph extends DirectedGraph<Tarea, Integer> {

    public List<Integer> dfs() {
        List<List<Pair<Integer, List<Integer>>>> pairs = new ArrayList<>();
        for (Vertex<Tarea, Integer> vertex : vertices) {
            pairs.add(dfsVisit(vertex, new ArrayList<>()));
        }
        // Recorre la lista de listas de pares, y busca de cada 1 de los pares, el maximo
        List<Pair<Integer, List<Integer>>> collect = pairs.stream()
                .map(x -> x.stream()
                        .max(Comparator.comparing(Pair::getKey))
                        .orElse(new Pair<>(0, Collections.emptyList())))
                .collect(Collectors.toList());
        // Recorremos nuevamente la lista buscando el maximo
        Optional<Pair<Integer, List<Integer>>> max = collect.stream()
                .max(Comparator.comparing(Pair::getKey));
        if(max.isPresent()) {
            return max.get().getValue();
        } else {
            return Collections.emptyList();
        }
    }

    /**
     *
     * @return un pair, donde la key va a ser el tiempo total y el value la lista de vertices recorridoxs
     */
    private List<Pair<Integer, List<Integer>>> dfsVisit(Vertex<Tarea, Integer> vertex, List<Pair<Integer, List<Integer>>> verticesRecorridos) {
        boolean multiBranch = false;
        // Si no tiene ningun vertice agregado signfica que es el primer vertice y agregamos el vertexId
        if(verticesRecorridos.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            list.add(vertex.getId());
            Pair<Integer, List<Integer>> e = new Pair<>(vertex.getValue().getDuración(), list);
            verticesRecorridos.add(e);
        } else {
            // Sino buscamos la ultima lista de vertices agregada, y se lo agregamos a la lista
            Pair<Integer, List<Integer>> integerListPair = verticesRecorridos.get(verticesRecorridos.size() - 1);
            integerListPair.setKey(integerListPair.getKey() + vertex.getValue().getDuración());
            integerListPair.getValue().add(vertex.getId());
        }
        Pair<Integer, List<Integer>> lastPair = verticesRecorridos.get(verticesRecorridos.size() - 1);
        List<Integer> aux = new ArrayList<>();
        int timeAux = 0;
        for (Iterator<Arc<Integer>> it = vertex.getArcs(); it.hasNext(); ) {
            Arc<Integer> next = it.next();
            Vertex<Tarea, Integer> adjacentVertex = findVertex(next.getVerticeDestino());
            // Si tiene mas de un adyacente agregamos el mismo elemento a la lista
            if(multiBranch) {
                Pair e = new Pair(timeAux, aux);
                lastPair = e;
                verticesRecorridos.add(e);
            } else {
                multiBranch = true;
                Pair<Integer, List<Integer>> integerListPair = lastPair;
                aux.addAll(integerListPair.getValue());
                timeAux = integerListPair.getKey();
            }
            lastPair.setKey(lastPair.getKey() + next.getEtiqueta());
            dfsVisit(adjacentVertex, verticesRecorridos);
        }
        return verticesRecorridos;
    }
}
