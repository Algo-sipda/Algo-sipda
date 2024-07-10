import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : tangerine){
            if(map.containsKey(t))
                map.put(t, map.get(t) + 1);
            else
                map.put(t, 1);
        }
        List<Integer> listKeySet = new ArrayList<>(map.keySet());
        
        Collections.sort(listKeySet, (v1, v2) -> (map.get(v2) - map.get(v1)));
        int cnt = 0; 
        for(int l : listKeySet){
            cnt += map.get(l);
            answer++;
            if(cnt >= k)
                break;
        }
                        
        return answer;
    }
}