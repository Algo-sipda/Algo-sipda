import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> chulsu = new HashMap<>();
        Map<Integer, Integer> brother = new HashMap<>();
        
        for(int t: topping) {
            brother.put(t, brother.getOrDefault(t, 0)+ 1);
        }
        
        for(int t: topping) {
            chulsu.put(t, chulsu.getOrDefault(t, 0) + 1);
            
            if(brother.get(t)-1 == 0) {
                brother.remove(t);
            }
            else {
                brother.put(t, brother.get(t)-1);
            }
            
            if(chulsu.size() == brother.size()) answer++;
        }
        return answer;
    }
}