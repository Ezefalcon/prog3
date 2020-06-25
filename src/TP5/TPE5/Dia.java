package TP5.TPE5;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by efalcon
 */
public class Dia {
    private int dia;
    private List<Familia> familias;
    private int miembros = 0;
    private final int cantidadMaximaDeMiembros;

    public Dia(int dia, int cantidadMaximaDeMiembros) {
        this.familias = new ArrayList<>();
        this.dia = dia;
        this.cantidadMaximaDeMiembros = cantidadMaximaDeMiembros;
    }

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

    public void eliminarFamilia(Familia familia) {
        this.familias.remove(familia);
    }

    public boolean tieneLugar(int miembros) {
        return this.miembros + miembros < cantidadMaximaDeMiembros;
    }

    /**
     * Agrega la familia si hay lugar
     * @param familia
     * @return si se pudo agregar la familia o no
     */
    public boolean agregarFamilia(Familia familia) {
        if(tieneLugar(familia.getMiembros())) {
            this.familias.add(familia);
            this.miembros += familia.getMiembros();
            return true;
        }
        return false;
    }

    public void sacarFamilia(Familia familia) {
        if(this.familias.remove(familia)) {
            this.familias.add(familia);
            this.miembros -= familia.getMiembros();
        }
    }

    @Override
    public String toString() {
        return "Dia{" +
                "dia=" + dia +
                ", familias=" + familias +
                ", miembros=" + miembros +
                ", cantidadMaximaDeMiembros=" + cantidadMaximaDeMiembros +
                '}';
    }
}
