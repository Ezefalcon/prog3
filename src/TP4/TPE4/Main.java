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
        int bono = 0;
        for (Familia familia: familias) {
            bono += agregarDiaPreferido(familia, map);
        }
        // No funciona
//        for (Familia familia: familias) {
//            bono += buscarLugarAlternativo(familia, map);
//        }
        System.out.println(bono);
        System.out.println(getMapSize(map, familias));
    }

    public static int getMapSize(Map<Integer, Pair<Integer, List<Familia>>> map, List<Familia> familias) {
        int size = 0;
        List<Familia> familias2 = new ArrayList<>();
        for (Map.Entry<Integer, Pair<Integer, List<Familia>>> entry : map.entrySet()) {
            Pair<Integer, List<Familia>> y = entry.getValue();
            size += y.getValue().size();
            familias2.addAll(y.getValue());
        }
        Optional<Familia> first = familias.stream().filter(x -> !familias2.contains(x)).findFirst();
        return size;
    }

    public static int getBonoCompensatorio(Familia familia, int diaPreferido) {
        return diaPreferido != 0 ?  25 + ( 10 * familia.getMiembros()) + (5 * diaPreferido) : 0;
    }

    /**
     *
     * @param familia
     * @param map
     * @return bono
     */
    public static int agregarDiaPreferido(Familia familia, Map<Integer, Pair<Integer, List<Familia>>> map) {
        for(int i = 0; i < CANT_DIAS_PREFERIDOS; i++) {
            Pair<Integer, List<Familia>> integerListPair = map.get(familia.getPreferenciaEn(i));
            if(integerListPair.getKey() + familia.getMiembros() < DISPONIBILIDAD_DIARIA) {
                integerListPair.setKey(integerListPair.getKey() + familia.getMiembros());
                integerListPair.getValue().add(familia);
                return getBonoCompensatorio(familia, i);
            }
        }
        int i = buscarLugarAlternativo(familia, map);
        if(i != -1) {
            return i;
        }
        System.out.println(familia);
        throw new IllegalArgumentException();
    }

    /**
     *
     * @param familia
     * @param map
     * @return -1 si la familia no tiene lugar alternativo sino retorna el bono
     */
    public static int buscarLugarAlternativo(Familia familia, Map<Integer, Pair<Integer, List<Familia>>> map) {
        for(int i = 0; i < CANT_DIAS_PREFERIDOS; i++) {
            int currentDiaPreferido = familia.getPreferenciaEn(i);
            Pair<Integer, List<Familia>> integerListPair = map.get(currentDiaPreferido);
            int finalI = i;
            Familia foundFamily = integerListPair.getValue()
                    .stream()
                    .filter(x -> x.getIndiceDePreferencia(currentDiaPreferido) > finalI || (x.getIndiceDePreferencia(currentDiaPreferido) == finalI && x.getMiembros() > familia.getMiembros()))
                    .findFirst()
                    .orElse(null);
            if(Objects.nonNull(foundFamily)) {
                if(foundFamily.equals(familia)) {
                    return -1;
                }
                try {
                    int i1 = agregarDiaPreferido(foundFamily, map);
                    if(i1 != -1) {
                        boolean remove = integerListPair.getValue().remove(foundFamily);
                        if(remove) {
                            integerListPair.getValue().add(familia);
                            return getBonoCompensatorio(familia, i) -  i1;
                        } else return 0;
                    }
                } catch(IllegalArgumentException e) {
                }

            }
        }
        return -1;
    }

    public static int reduceBonoNotWorking1(Familia familia, Map<Integer, Pair<Integer, List<Familia>>> map, int bono) {
        for(int i = 0; i < CANT_DIAS_PREFERIDOS; i++) {
            int currentDiaPreferido = familia.getPreferenciaEn(i);
            Pair<Integer, List<Familia>> integerListPair = map.get(currentDiaPreferido);
            int finalI = i;
            Familia foundFamily = integerListPair.getValue()
                    .stream()
                    .filter(x -> x.getIndiceDePreferencia(currentDiaPreferido) > finalI || (x.getIndiceDePreferencia(currentDiaPreferido) == finalI && x.getMiembros() > familia.getMiembros()))
                    .findFirst()
                    .orElse(null);
            if(Objects.nonNull(foundFamily)) {
                int i1 = agregarloEnOtroLugar(foundFamily, map);
                if(i1 != -1) {
                    boolean remove = integerListPair.getValue().remove(foundFamily);
                    if(remove) {
                        bono -= getBonoCompensatorio(foundFamily, foundFamily.getIndiceDePreferencia(currentDiaPreferido));
                        integerListPair.getValue().add(familia);
                        return bono + getBonoCompensatorio(familia, i) + i1;
                    } else return 0;
                }
            }
        }
        return bono;
    }

    private static int agregarloEnOtroLugar(Familia foundFamily, Map<Integer, Pair<Integer, List<Familia>>> map) {
        try {
            return agregarDiaPreferido(foundFamily, map);
        } catch(IllegalArgumentException e) {
            return -1;
        }
    }

}
