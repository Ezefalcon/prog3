package TP5.TPE5;

import java.util.*;

public class Main {


    public static void main(String... args) {


        CSVReader reader = new CSVReader("resources/familias-1.csv");

        ArrayList<Familia> familias = reader.read();

        BonoFamiliar bonoFamiliar = new BonoFamiliar(familias, 10);
        int bono1 = bonoFamiliar.agregarFamiliasBacktracking();
        System.out.println("Minimo bono de Familias-1 -> " + bono1);


        CSVReader reader2 = new CSVReader("resources/familias-2.csv");

        ArrayList<Familia> familias2 = reader2.read();

        BonoFamiliar bonoFamiliar2 = new BonoFamiliar(familias2, 10);
        int bono2 = bonoFamiliar2.agregarFamiliasBacktracking();
        System.out.println("Minimo bono de Familias-2 -> " + bono2);

    }

}