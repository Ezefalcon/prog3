package TP4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by efalcon
 */
public class CoinChanger {

    private List<Integer> coins;

    public CoinChanger(List<Integer> coins) {
        this.coins = coins;
        // Sort the provided coins just in case
        Collections.sort(coins, Collections.reverseOrder());
    }

    public List<Integer> getChange(int value) {
        List<Integer> changeList = new ArrayList<>();
        while(value >= 1) {
            int changeInCoins = getChangeInCoins(value);
            if(changeInCoins != 0) {
                changeList.add(changeInCoins);
                value -= changeInCoins;
            }
        }
        return changeList;
    }

    private int getChangeInCoins(int value) {
        return coins.stream()
                .filter(coin -> coin <= value)
                .findFirst()
                .orElse(0);
    }

    public static void main(String[] args) {
        CoinChanger coinChanger = new CoinChanger(Arrays.asList(100, 25, 10, 5, 1));
        List<Integer> change = coinChanger.getChange(289);
        System.out.println(change);
    }
}
