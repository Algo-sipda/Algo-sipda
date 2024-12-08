import java.io.*;
import java.util.*;

class Solution {

    static Map<String, Integer> map = new HashMap<>();
    static String[] product;
    static int[] count;

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        product = want;
        count = number;

        for(int i = 0; i < 10; i++) {
            addProduct(discount[i]);
        }

        if(isPurchaseAvailable()) answer++;

        for(int i = 10; i < discount.length; i++) {
            map.put(discount[i - 10], map.get(discount[i - 10]) - 1);
            addProduct(discount[i]);
            if(isPurchaseAvailable()) answer++;
        }
        return answer;
    }

    public boolean isPurchaseAvailable() {
        for(int i = 0; i < product.length; i++) {
            if(!map.containsKey(product[i])) return false;

            if(map.get(product[i]) < count[i]) return false;
        }
        return true;
    }

    public void addProduct(String product) {
        if(!map.containsKey(product)) {
            map.put(product, 1);
        } else {
            map.put(product, map.get(product) + 1);
        }
    }
}