import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for(int num: tangerine) {
            if(hm.containsKey(num)) {
                hm.replace(num, hm.get(num)+1);
            }else {
                hm.put(num, 1);
            }
        }
        
        List<Integer> list = new ArrayList<>(hm.keySet());
        
        Collections.sort(list, (o1, o2)->(hm.get(o2) - hm.get(o1)));
        
        int cnt = 0;
        int answer = 0;
        for (Integer key : list) {
            if(cnt >= k) {
                break;
            }
            cnt += hm.get(key);
            answer++;
		}
        return answer;
    }
}