package TP5.TPE5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BonoFamiliar {
    private List<Dia> dias;
    private List<Familia> familias;
    private int bonoActual = 0;
    private int bonoMinimoEncontrado = Integer.MAX_VALUE;
    private int counter = 0;
    private final static int CANTIDAD_MAXIMA_MIEMBROS = 30;

    /**
     * Se inicializa la clase para poder realizar los calculos necesarios
     * @param familias
     * @param cantidadDeDias en los que se va a querer insertar el
     */
    public BonoFamiliar(List<Familia> familias, int cantidadDeDias) {
        this.familias = familias;
        dias = new ArrayList<>();
        // Inicializamos los dias
        for(int i = 1; i <= cantidadDeDias; i++) {
            dias.add(new Dia(i, CANTIDAD_MAXIMA_MIEMBROS));
        }
    }


    public static int getBonoCompensatorio(Familia familia, int diaPreferido) {
        return diaPreferido != 0 ?  25 + ( 10 * familia.getMiembros()) + (5 * diaPreferido) : 0;
    }

    public int agregarFamiliasBacktracking() {
        agregarFamiliasADias(0);
        System.out.println(counter);
//        System.out.println(dias);
//        Integer integer = dias.stream().map(x -> x.getMiembros()).reduce((x, y) -> x + y).orElse(0);
        return bonoMinimoEncontrado;
    }

    /**
     * Intenta encontrar el menor bono compensatorio intentando poner cada familia en un lugar diferente
     * @param index
     */
    private void agregarFamiliasADias(int index) {
        counter++;
        if(index == familias.size()) {
            if(bonoMinimoEncontrado > bonoActual) {
                bonoMinimoEncontrado = bonoActual;
            }
        } else {
            Familia familia = familias.get(index);
            for(int i = 0; i < 3; i++) {
                Dia dia = dias.get(familia.getPreferenciaEn(i) - 1);
                if(dia.agregarFamilia(familia)) {
                    int bonoCompensatorio = getBonoCompensatorio(familia, i);
                    bonoActual += bonoCompensatorio;
                    if(bonoMinimoEncontrado > bonoActual) {
                        agregarFamiliasADias(index+1);
                    }
                    bonoActual -= bonoCompensatorio;
                    dia.sacarFamilia(familia);
                }
            }
        }
    }

}
