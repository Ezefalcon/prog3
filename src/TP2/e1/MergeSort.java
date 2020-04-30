package TP2.e1;

import java.util.Arrays;
import java.util.List;

public class MergeSort {
    private int[] numbers;
    private int[] helper;
    private int size;

    public static void main(String[] args) {
        int[] integers = new int[]{32, 12, 345, 2, 1, 3, 1, 2, 0, 22};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(integers);
        SortedArray.printArray(integers);
    }

    public void sort(int[] values) {
        this.numbers = values;
        size = values.length;
        this.helper = new int[size];
        mergesort(0, size - 1);
    }

    private void mergesort(int low, int high) {
        // si low es menor que high continua el ordenamiento
        // si low no es menor que high entonces el array está ordenado
        // ya que es el caso base donde el array tiene un solo elemento.
        if (low < high) {
            // obtener el indice del elemento que se encuentra en la mitad
            // al ser int redondea el resultado al entero menor
            int middle = (low + high) / 2;
            // ordenar la mitad izquierda del array
            mergesort(low, middle);
            // ordenar la mitad derecha del array
            mergesort(middle + 1, high);
            // combiner ambas mitades ordenadas
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {
        // copier ambas partes al array helper
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;
        // copiar de manera ordenada al array original los valores de la
        // mitad izquierda o de la derecha
        while (i <= middle && j <= high) {
            if (helper[ i ] <= helper[ j ]) {
                numbers[ k ] = helper[ i ];
                i++;
            } else {
                numbers[ k ] = helper[ j ];
                j++;
            }
            k++;
        }
        // si quedaron elementos copiarlos al array original
        while (i <= middle) {
            numbers[ k ] = helper[ i ];
            k++;
            i++;
        }
        while (j <= high) {
            numbers[ k ] = helper[ j ];
            k++;
            j++;
        }
    }
}