package TP3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by efalcon
 */
public class DirectedGraph<T,V> implements Graph<T,V>{

    private List<Vertex<T,V>> vertices;

    public DirectedGraph() {
        this.vertices = new LinkedList<>();
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
     * Complejidad: O((cantidadDeVertices * 2) * cantidadDeArcos)
     * @param verticeId -> Id del vertice
     */
    @Override
    public void borrarVertice(int verticeId) {
        int size = vertices.size();
        vertices = vertices.stream()
                .filter(tVertex -> tVertex.getId() != verticeId)
                .collect(Collectors.toList());
        if(size != vertices.size()) {
             vertices.forEach(x -> x.removeArc(verticeId));
        }
    }

    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcosDelVertice)
     * @param verticeId1 -> Id del vertice origen
     * @param verticeId2 -> Id del vertice destino
     * @param etiqueta -> Valor de etiqueta
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        Vertex<T, V> vertex = findVertex(verticeId1);// Encontramos el primer vertice
        if(Objects.nonNull(vertex) && !existeArco(verticeId1,verticeId2)) {
            vertex.addArc(verticeId2, etiqueta); // Agregamos el arco
        }
    }

    private Vertex<T, V> findVertex(int verticeId) {
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
        Vertex<T, V> vertex = findVertex(verticeId1);
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
        Vertex<T, V> vertex = findVertex(verticeId1);
        if(Objects.isNull(vertex)) return false;
        return vertex.existsArc(verticeId2);
    }

    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcos)
     * @param verticeId1 -> Id del vertice origen
     * @param verticeId2 -> Id del vertice destino
     * @return el arco || null
     */
    @Override
    public Arc<T> obtenerArco(int verticeId1, int verticeId2) {
        Vertex<T, V> vertex = findVertex(verticeId1);
        return Objects.nonNull(vertex) ? vertex.getArc(verticeId2) : null;
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
    public Iterator<Vertex<T, V>> obtenerVertices() {
        return vertices.iterator();
    }

    /**
     * Complejidad: O(vertices)
     * @param verticeId
     * @return
     */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        Vertex<T, V> vertex = findVertex(verticeId);
        return Objects.nonNull(vertex) ? vertex.getVertexOfArcs() : Collections.emptyIterator();
    }

    /**
     * Complejidad: O(vertices)
     * Trae todos los arcos
     * @return un iterador de arcos
     */
    @Override
    public Iterator<Arc<V>> obtenerArcos() {
        return new ArcIterator<>(vertices.stream()
                .map(x -> x.getArcs())
                .collect(Collectors.toList()));
    }

    /**
     * Complejidad: O(cantidadDeVertices + cantidadDeArcos)
     * @param verticeId -> Id del vertice
     * @return iterador de arcos del vertice
     */
    @Override
    public Iterator<Arc<V>> obtenerArcos(int verticeId) {
        Vertex<T, V> vertex = findVertex(verticeId);
        if(Objects.nonNull(vertex)) {
            return vertex.getArcs();
        }
        return Collections.emptyIterator();
    }

}
