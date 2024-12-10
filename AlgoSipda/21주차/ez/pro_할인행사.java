import java.util.*;

class pro_할인행사 {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }

        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> copy = new HashMap<>(map);
            if (canBuy(copy, discount, i)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean canBuy(Map<String, Integer> map, String[] discount, int start) {
        for (int i = start; i < start + 10; i++) {
            if (map.containsKey(discount[i])) {
                map.put(discount[i], map.get(discount[i]) - 1);
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key) > 0) {
                return false;
            }
        }
        return true;
    }
}