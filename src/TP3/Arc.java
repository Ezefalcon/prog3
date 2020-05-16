package TP3;

/**
 * Created by efalcon
 */
public class Arc<V> {

    private int verticeOrigen;
    private int verticeDestino;
    private V etiqueta;

    public Arc(int verticeOrigen, int verticeDestino, V etiqueta) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

    public V getEtiqueta() {
        return etiqueta;
    }
}
