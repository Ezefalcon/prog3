package TP3;

import java.util.Iterator;
import java.util.List;

/**
 * Created by efalcon
 */
public class VertexIterator implements Iterator<Vertex> {

    private Iterator<Vertex> vertices;

    public VertexIterator(List<Vertex> list) {
        this.vertices = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return vertices.hasNext();
    }

    @Override
    public Vertex next() {
        return vertices.next();
    }
}
