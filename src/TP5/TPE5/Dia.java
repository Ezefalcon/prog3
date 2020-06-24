package TP5.TPE5;


import java.util.List;

/**
 * Created by efalcon
 */
public class Dia {
    private int dia;
    private List<Familia> familias;
    private int miembros;

    public int getMiembros() {
        return miembros;
    }

    public void setMiembros(int miembros) {
        this.miembros = miembros;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public List<Familia> getFamilias() {
        return familias;
    }

    public void setFamilias(List<Familia> familias) {
        this.familias = familias;
    }
}
