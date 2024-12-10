import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        HashMap<String, Integer> hm = new HashMap<>();
        for(int i=0;i<want.length;i++) {
            hm.put(want[i], number[i]);
        }
        
        for(int i=0;i<discount.length - 9 ;i++) {
            HashMap<String, Integer> disHm = new HashMap<>();
            for(int j=0;j<10;j++) {
                disHm.put(discount[i+j], disHm.getOrDefault(discount[i+j], 0)+1);
            }
            
            int check = 1;
            
            for(String key: hm.keySet()) {
                if(hm.get(key) != disHm.get(key)) {
                    check = 0;
                    break;
                }
            }
            
            answer += check;
        }
        
        return answer;
    }
}