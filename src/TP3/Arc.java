package TP3;

/**
 * Created by efalcon
 */
public class Arc<T> {

    private int verticeOrigen;
    private int verticeDestino;
    private T etiqueta;

    public Arc(int verticeOrigen, int verticeDestino, T etiqueta) {
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

    public T getEtiqueta() {
        return etiqueta;
    }
}
