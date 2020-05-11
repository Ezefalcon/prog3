package TP3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by efalcon
 */
public class Vertex<T> {
    int id;
    T value;
    List<Arc> arcs;

    public Vertex(int id) {
        this.id = id;
        this.arcs = new LinkedList<>();
    }

    public void addArc(int vertexTo, T etiqueta) {
        // Checkear si el arco no existe
        if(!existsArc(vertexTo)) {
            arcs.add(new Arc(this.id, vertexTo, etiqueta));
        }
    }

    public boolean existsArc(int vertexTo) {
        Arc arc = this.arcs.stream()
                .filter(vertex -> vertex.getVerticeOrigen() == this.id && vertex.getVerticeDestino() == vertexTo)
                .findFirst()
                .orElse(null);
        return Objects.nonNull(arc);
    }

    public void removeArc(int vertexTo) {
        this.arcs = arcs.stream()
                .filter(arc -> arc.getVerticeOrigen() == this.id && arc.getVerticeDestino() == vertexTo)
                .collect(Collectors.toList());
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

    public List<Arc<T>> getArcs() {
        return new ArrayList<>(arcs);
    }

    public void setArcs(LinkedList<Arc> arcs) {
        this.arcs = arcs;
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
}
