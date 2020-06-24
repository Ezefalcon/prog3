package TP5.TPE5;

import java.util.*;

public class Main {

    private static final int DIAS = 10;
    private static final int DISPONIBILIDAD_DIARIA = 30;
    private static final int CANT_DIAS_PREFERIDOS = 3;


    public static void main(String... args) {


        CSVReader reader = new CSVReader("resources/familias-1.csv");

        ArrayList<Familia> familias = reader.read();

        // Se ordenan las familias por miembros
        familias.sort(Comparator.comparingInt(Familia::getMiembros));
        for (Familia familia: familias)
            System.out.println(familia);



//        CSVReader reader2 = new CSVReader("resources/familias-2.csv");
//
//        ArrayList<Familia> familias2 = reader2.read();
//
//        for (Familia familia: familias2)
//            System.out.println(familia);
    }

    public static List<Dia> agregarFamiliasEnDias(List<Familia> familias) {

    }

}