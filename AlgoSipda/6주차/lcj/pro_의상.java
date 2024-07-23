import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> hs = new HashMap<>();
        
        for(int i=0;i<clothes.length;i++){
            String type = clothes[i][1];
            if(hs.containsKey(type)){
                int tmp = hs.get(type);
                hs.put(type, tmp+1);
            }else{
                hs.put(type, 1);
            }
        }
        
        for(String str: hs.keySet()){
            answer *= hs.get(str)+1;
        }
        
        answer -= 1;
        
        return answer;
    }
}