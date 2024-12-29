import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for(int t : topping) {
            if(map.containsKey(t)) {
                map.put(t, map.get(t) + 1);
            } else {
                map.put(t, 1);
            }
        }

        for(int t : topping) {
            map.put(t, map.get(t) - 1);
            set.add(t);
            if(map.get(t) == 0) {
                map.remove(t);
            }
            if(map.size() == set.size()) {
                answer++;
            }
        }
        return answer;
    }
}
