package TP3;

import java.util.Iterator;

/**
 * Created by efalcon
 */
public interface Graph<T,V> {

    // Agrega un vertice
    void agregarVertice(int verticeId, T value);

    // Borra un vertice
    void borrarVertice(int verticeId);

    // Agrega un arco con una etiqueta, que conecta el verticeId1 con el verticeId2
    void agregarArco(int verticeId1, int verticeId2, V etiqueta);

    // Borra el arco que conecta el verticeId1 con el verticeId2
    void borrarArco(int verticeId1, int verticeId2);

    // Verifica si existe un vertice
    boolean contieneVertice(int verticeId);

    // Verifica si existe un arco entre dos vertices
    boolean existeArco(int verticeId1, int verticeId2);

    // Obtener el arco que conecta el verticeId1 con el verticeId2
    Arc<T> obtenerArco(int verticeId1, int verticeId2);

    // Devuelve la cantidad total de vertices en el grafo
    int cantidadVertices();

    // Devuelve la cantidad total de arcos en el grafo
    int cantidadArcos();

    // Obtiene un iterador que me permite recorrer todos los vertices almacenados en el grafo
    Iterator<Integer> obtenerVertices();

    // Obtiene un iterador que me permite recorrer todos los vertices adyacentes a verticeId
    Iterator<Integer> obtenerAdyacentes(int verticeId);

    // Obtiene un iterador que me permite recorrer todos los arcos del grafo
    Iterator<Arc<V>> obtenerArcos();

    // Obtiene un iterador que me permite recorrer todos los arcos que parten desde verticeId
    Iterator<Arc<V>> obtenerArcos(int verticeId);
}
