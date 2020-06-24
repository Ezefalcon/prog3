package TP5.TPE5;

import java.util.Arrays;
import java.util.Objects;

/* Una familia, con su cantidad de dias, y una arreglo con el top de 4 dias preferidos */
public class Familia {

    private int id;
    private int miembros;
    private int[] diasPreferidos;

    public Familia(int id, int miembros, int... diasPreferidos) {
        this.id = id;
        this.miembros = miembros;
        this.diasPreferidos = diasPreferidos;
    }

    /* Id de la familia */
    public int getId() {
        return id;
    }

    /* Retorna la cantidad de miembros de la familia. */
    public int getMiembros() {
        return miembros;
    }


    /* Dado un indice entre 0 y 4, retorna el día preferido por la familia para ese indice. */
    public int getPreferenciaEn(int indice) {
        return this.diasPreferidos[indice];
    }

    /* Retorna el día preferido de la familia */
    public int getDiaPreferido() {
        return getPreferenciaEn(0);
    }

    /* Dado un dia pasado por parametro, indica el orden de ese dia en el top 5 de preferencias.
    Si el dia no forma parte de las preferencias del usuario, se retorna -1. */
    public int getIndiceDePreferencia(int dia) {
        for (int indice = 0; indice < diasPreferidos.length; indice++)
            if (dia == diasPreferidos[indice])
                return indice;
        return -1;
    }

    @Override
    public String toString() {
        return "Familia: id=" + id + ", miembros=" + miembros + ", preferencias=" + Arrays.toString(diasPreferidos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Familia familia = (Familia) o;
        return id == familia.id &&
                miembros == familia.miembros &&
                Arrays.equals(diasPreferidos, familia.diasPreferidos);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, miembros);
        result = 31 * result + Arrays.hashCode(diasPreferidos);
        return result;
    }
}
