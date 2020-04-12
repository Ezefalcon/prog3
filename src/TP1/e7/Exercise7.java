package TP1.e7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise7 {
    public static void main(String[] args) {
        List<Integer> listB = Arrays.asList(23,231,3232,11,22,334,53,2);
        List<Integer> listB2 = Arrays.asList(231,23,24,212345,12,33,43,22,11);
        List<Integer> result = listB.stream()
                .filter(x -> !listB2.contains(x))
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
