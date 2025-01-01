import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> chulsuMap = new HashMap<>();
        Set<Integer> broSet = new HashSet<>();

        for (int t : topping) {
            chulsuMap.put(t, chulsuMap.getOrDefault(t, 0) + 1);
        }

        int chulsuTopping = chulsuMap.size();
        int broTopping = 0;
        int answer = 0;

        for (int t : topping) {
            chulsuMap.put(t, chulsuMap.get(t) - 1);
            if (chulsuMap.get(t) == 0) {
                chulsuMap.remove(t);
                chulsuTopping--;
            }

            if (broSet.add(t)) 
                broTopping++;

            if (chulsuTopping == broTopping) 
                answer++;
        }

        return answer;
    }
}
