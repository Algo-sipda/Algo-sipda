import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        int len = clothes.length;
        for (int i = 0; i < len; i++) {
            String key = clothes[i][1];
            if (map.containsKey(key))
                map.put(key, map.get(key) + 1);
            else
                map.put(key, 1);
        }

        int answer = 1;
        for (int value : map.values()) {
            answer *= (value + 1);
        }
        return answer - 1;
    }
}