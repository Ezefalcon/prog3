package TP3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by efalcon
 */
public class DirectedGraph<T> implements Graph<T>{

    private List<Vertex<T>> vertices;

    public DirectedGraph() {
        this.vertices = new LinkedList<>();
    }

    public static void main(String[] args) {
        DirectedGraph<Integer> directedGraph = new DirectedGraph<>();
        directedGraph.agregarVertice(12);
        directedGraph.agregarVertice(15);
        directedGraph.agregarVertice(17);
        directedGraph.agregarArco(12,15,20);
        System.out.println(directedGraph.existeArco(12,15));
    }

    /**
     * Complejidad: O(cantidadDeVertices)
     * Agrega un vertice al grafo
     * @param verticeId -> Id del vertice
     */
    @Override
    public void agregarVertice(int verticeId) {
        if(!contieneVertice(verticeId)) {
            vertices.add(new Vertex(verticeId));
        }
    }

    /**
     * Complejidad: O(cantidadDeVertices)
     * @param verticeId -> Id del vertice
     */
    @Override
    public void borrarVertice(int verticeId) {
        vertices = vertices.stream()
                .filter(tVertex -> tVertex.getId() != verticeId)
                .collect(Collectors.toList());
    }

    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcos)
     * @param verticeId1 -> Id del vertice origen
     * @param verticeId2 -> Id del vertice destino
     * @param etiqueta -> Valor de etiqueta
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        Vertex<T> vertex = findVertex(verticeId1);// Encontramos el primer vertice
        if(Objects.nonNull(vertex) && !existeArco(verticeId1,verticeId2)) {
            vertex.addArc(verticeId2, etiqueta); // Agregamos el arco
        }
    }

    private Vertex<T> findVertex(int verticeId) {
        return vertices.stream()
                .filter(vertex -> vertex.getId() == verticeId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcos)
     * @param verticeId1 -> Id del vertice origen
     * @param verticeId2 -> Id del vertice destino
     */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        Vertex<T> vertex = findVertex(verticeId1);
        if(Objects.nonNull(vertex)) {
            vertex.removeArc(verticeId2);
        }
    }

    /**
     * Complejidad: O(cantidadDeVertices)
     * @param verticeId -> Id del vertice
     * @return si lo contiene o no
     */
    @Override
    public boolean contieneVertice(int verticeId) {
        return vertices.stream().anyMatch(x -> x.getId() == verticeId);
    }

    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcos)
     * @param verticeId1 -> Id del vertice origen
     * @param verticeId2 -> Id del vertice destino
     * @return si existe un arco del vertice1 al vertice2
     */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return Objects.nonNull(obtenerArco(verticeId1,verticeId2));
    }

    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcos)
     * @param verticeId1 -> Id del vertice origen
     * @param verticeId2 -> Id del vertice destino
     * @return el arco || null
     */
    @Override
    public Arc<T> obtenerArco(int verticeId1, int verticeId2) {
        Vertex<T> vertex = findVertex(verticeId1);
        if(Objects.nonNull(vertex)) {
            return vertex.getArc(verticeId2);
        }
        return null;
    }

    /**
     * Complejidad: O(1)
     * @return cantidad de vertices
     */
    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    /**
     * Complejidad: O(cantidadDeVertices)
     * @return cantidad de arcos || 0
     */
    @Override
    public int cantidadArcos() {
        return this.vertices.stream()
                .map(x -> x.getArcsSize())
                .reduce((x,y) -> x + y)
                .orElse(0);
    }

    /**
     * Complejidad: O(cantidadDeVertices)
     * @return iterador de vertices
     */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.stream().map(x -> x.getId()).iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        return null;
    }

    @Override
    public Iterator<Arc<T>> obtenerArcos() {
        return vertices.stream().map(x -> x.getArcs())
                .reduce((x, y) -> {
                    x.addAll(y);
                    return x;
                })
                .orElse(new ArrayList<>())
                .iterator();
    }


    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcos)
     * @param verticeId -> Id del vertice
     * @return iterador de arcos del vertice
     */
    @Override
    public Iterator<Arc<T>> obtenerArcos(int verticeId) {
        Vertex<T> vertex = findVertex(verticeId);
        if(Objects.nonNull(vertex)) {
            return vertex.getArcs().iterator();
        }
        return Collections.emptyIterator();
    }

}
