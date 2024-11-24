import java.util.*;

class Solution {
    public int solution(String name) {
        List<Character> alphabet = new ArrayList<>(Arrays.asList(
                                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
                                    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
                                    'U', 'V', 'W', 'X', 'Y', 'Z'));
        int len = name.length();
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < len; i++){
            int idx = alphabet.indexOf(name.charAt(i));
            answer += Math.min(idx , 26 - idx);
            if(idx != 0)
                list.add(i);
        }
        
        if(list.isEmpty())
            return answer;
        
        int gap = Math.min(list.get(list.size()-1), len - list.get(0));
        for(int i = 0; i < list.size() - 1; i++){
            gap = Math.min(gap, list.get(i) * 2 + (len - list.get(i+1)));
            gap = Math.min(gap, list.get(i) + (len - list.get(i+1)) * 2);
        }
        answer += gap;
        return answer;
    }
}