package TP4.TPE4;

import java.util.*;

public class Main {

    private static final int DIAS = 100;
    private static final int DISPONIBILIDAD_DIARIA = 340;
    private static final int CANT_DIAS_PREFERIDOS = 8;


    public static void main(String... args) {

        CSVReader reader = new CSVReader("resources/familias.csv");

        ArrayList<Familia> familias = reader.read();
        // La clave sera el dia y el valor un par con la cantidad total de miembros ya guardadas y las familias asignadas a ese dia
        Map<Integer, Pair<Integer, List<Familia>>> map = new HashMap<>();
        for(int i = 1; i <= DIAS; i++) {
            map.put(i, new Pair<>(0,new ArrayList<>(DISPONIBILIDAD_DIARIA)));
        }
        // Se ordenan las familias por miembros
        familias.sort(Comparator.comparingInt(Familia::getMiembros));

        int bono = 0;
        for (Familia familia: familias) {
            bono += agregarDiaPreferido(familia, map);
        }
        System.out.println("El bono total de las familias antes de la transformacion local es de " + bono);

        for (Familia familia: familias) {
            bono += buscarLugarAlternativo(familia, map);
        }
        System.out.println("El bono total de las familias despues de la transformacion local es de " + bono);

        System.out.println("Cantidad de personas agregadas "+ getMapSize(map));
    }

    public static int getBonoCompensatorio(Familia familia, int diaPreferido) {
        return diaPreferido != 0 ?  25 + ( 10 * familia.getMiembros()) + (5 * diaPreferido) : 0;
    }

    /**
     * Se intenta agregar el dia mas preferido dentro del Map
     * @param familia
     * @param map
     * @return el bono compensatorio o 0 si no se logro agregar la familia por espacio
     */
    public static int agregarDiaPreferido(Familia familia, Map<Integer, Pair<Integer, List<Familia>>> map) {
        // Recorro los dias preferidos de la familia
        for(int i = 0; i < CANT_DIAS_PREFERIDOS; i++) {
            Pair<Integer, List<Familia>> integerListPair = map.get(familia.getPreferenciaEn(i));
            // If hay espacio en este dia
            if(integerListPair.getKey() + familia.getMiembros() < DISPONIBILIDAD_DIARIA) {
                integerListPair.setKey(integerListPair.getKey() + familia.getMiembros()); // Actualiza la cantidad de miembros en ese dia
                integerListPair.getValue().add(familia); // Agrega la familia a la lista
                return getBonoCompensatorio(familia, i);
            }
        }
        System.out.println("No se pudo agregar la familia " + familia);
        return 0;
    }

    /**
     * Se busca un lugar alternativo para la familia dada
     * @param familia
     * @param map
     * @return el bono compensatorio total o 0 si no se realizo ningun cambio
     */
    public static int buscarLugarAlternativo(Familia familia, Map<Integer, Pair<Integer, List<Familia>>> map) {
        for (int i = 0; i < CANT_DIAS_PREFERIDOS; i++) {
            int currentDiaPreferido = familia.getPreferenciaEn(i);
            Pair<Integer, List<Familia>> integerListPair = map.get(currentDiaPreferido);
            int finalI = i;
            // Intenta encontrar una familia por la que puede reemplazarse
            List<Familia> familiasEnEsteDia = integerListPair.getValue();
            Familia foundFamily = familiasEnEsteDia
                    .stream()
                    .filter(x -> x.getIndiceDePreferencia(currentDiaPreferido) > finalI)
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(foundFamily)) {
                // Si se encontro la familia, buscamos si se puede meter en un lugar diferente
                for (int j = 0; j < CANT_DIAS_PREFERIDOS; j++) {
                    if(j == i) continue; // No buscar en el lugar que se encuentra la familia
                    Pair<Integer, List<Familia>> mapPair = map.get(foundFamily.getPreferenciaEn(j));
                    List<Familia> familiasEnJ = mapPair.getValue();
                    for(Familia f: familiasEnJ) {
                        if(integerListPair.getKey() + f.getMiembros() < DISPONIBILIDAD_DIARIA && (getBonoCompensatorio(foundFamily, j) < getBonoCompensatorio(foundFamily, i))) {
                            // Si existe un lugar intercambiamos lugares
                            int bono = 0;
                            familiasEnEsteDia.remove(foundFamily); // Se elimina la familia encontrada
                            familiasEnEsteDia.add(familia);
                            familiasEnJ.add(foundFamily);
                            bono -= searchAndDestroyFamilia(familia, map); // Se elimina la familia que se quiere reemplazar y se resta el bono
                            bono -= getBonoCompensatorio(foundFamily, i); // Se resta el bono compensatorio calculado anteriormente
                            bono += getBonoCompensatorio(familia, i); // Se suma el nuevo bono
                            bono += getBonoCompensatorio(foundFamily, j); // Se suma el nuevo bono
                            return bono;
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Busca la familia, la elimina y devuelve el bono compensatorio
     * @param familia
     * @param map
     * @return el bono de dicha familia
     */
    public static int searchAndDestroyFamilia(Familia familia, Map<Integer, Pair<Integer, List<Familia>>> map) {
        for (int i = 0; i < CANT_DIAS_PREFERIDOS; i++) {
            Pair<Integer, List<Familia>> mapPair = map.get(familia.getPreferenciaEn(i));
            if(mapPair.getValue().remove(familia)) {
                return getBonoCompensatorio(familia, i);
            }
        }
        return 0;
    }


    /**
     * Calcula la cantidad de familias en el Map
     * @param map
     * @return cantidad de familias insertadas dentro del mapa
     */
    public static int getMapSize(Map<Integer, Pair<Integer, List<Familia>>> map) {
        int size = 0;
        for (Map.Entry<Integer, Pair<Integer, List<Familia>>> entry : map.entrySet()) {
            Pair<Integer, List<Familia>> y = entry.getValue();
            size += y.getValue().size();
        }
        return size;
    }

}
