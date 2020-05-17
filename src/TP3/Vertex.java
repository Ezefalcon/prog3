package TP3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by efalcon
 */
public class Vertex<T,V> {
    private int id;
    private T value;
    private List<Arc<V>> arcs;
    private Color color;

    public Vertex(int id) {
        this.id = id;
        this.arcs = new LinkedList<>();
    }

    public Vertex(int id, T value) {
        this.id = id;
        this.value = value;
        this.arcs = new LinkedList<>();
    }

    public void addArc(int vertexTo, V etiqueta) {
        // Checkear si el arco no existe
        if(!existsArc(vertexTo)) {
            arcs.add(new Arc(this.id, vertexTo, etiqueta));
        }
    }

    public boolean existsArc(int vertexTo) {
        return this.arcs.stream()
                .filter(vertex -> vertex.getVerticeOrigen() == this.id && vertex.getVerticeDestino() == vertexTo)
                .findFirst()
                .isPresent();
    }

    public void removeArc(int vertexTo) {
        this.arcs = arcs.stream()
                .filter(arc -> arc.getVerticeOrigen() == this.id && arc.getVerticeDestino() == vertexTo)
                .collect(Collectors.toList());
    }

    public Iterator<Integer> getVertexOfArcs() {
        return arcs.stream().map(x -> x.getVerticeOrigen()).iterator();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Iterator<Arc<V>> getArcs() {
        return arcs.iterator();
    }

    public Arc getArc(int verticeId2) {
        return arcs.stream()
                .filter(x -> x.getVerticeOrigen() == this.id && x.getVerticeDestino() == verticeId2)
                .findFirst()
                .orElse(null);
    }

    public int getArcsSize() {
        return this.arcs.size();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
