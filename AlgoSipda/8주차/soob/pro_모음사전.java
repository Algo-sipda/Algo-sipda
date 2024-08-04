import java.util.*;

class Solution {
    public int solution(String word) {
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('E', 1);
        map.put('I', 2);
        map.put('O', 3);
        map.put('U', 4);
        
        int[] plus = new int[]{781,156,31,6,1};
        
        int answer = word.length();
        for(int i = 0; i < word.length(); i++){
            int n = map.get(word.charAt(i));
            answer += n * plus[i];
        }
        
        return answer;
    }
}