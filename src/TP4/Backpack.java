package TP4;

import TP3.Pair;

import java.util.*;

/**
 * Created by efalcon
 */
public class Backpack {
    private int weight;

    public Backpack(int weight) {
        this.weight = weight;
    }

    public Map<Double, Pair> fillBackpack(LinkedList<Pair<Double, Double>> items) {
        Map<Double, Pair> map = new HashMap<>();
        double w = 0;
        items.sort(Comparator.comparingDouble(x -> x.getKey() / x.getValue()));
        while(w < weight && !items.isEmpty()) {
            Pair<Double, Double> pair = items.pollFirst();
            System.out.println(pair);
            Double key = pair.getKey();
            if(key > w) {
                double res = key / weight;
                w += res * key;
                map.put(res, pair);
            } else {
                w += key;
                map.put(1D, pair);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        Pair<Double, Double> pair = new Pair(18D,25D);
        Pair<Double, Double> pair2 = new Pair(15D,24D);
        Pair<Double, Double> pair3 = new Pair(10D,15D);
        LinkedList<Pair<Double, Double>> items = new LinkedList<>();
        items.addAll(Arrays.asList(pair, pair2, pair3));
        Backpack backpack = new Backpack(20);
        Map<Double, Pair> doublePairMap = backpack.fillBackpack(items);
        System.out.println(doublePairMap);
    }
}
