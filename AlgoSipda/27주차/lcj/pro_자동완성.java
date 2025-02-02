import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();
        
        for(String word: words) {
            list.add(word);
        }
        Collections.sort(list);
        
        int value = overlapLen(list.get(0), list.get(1));
        
        if(value < list.get(0).length()) answer += (value+1);
        else answer += value;
        
        for(int i=1;i<words.length-1;i++) {
            value = Math.max(overlapLen(list.get(i), list.get(i+1)), overlapLen(list.get(i), list.get(i-1)));

            if(value < list.get(i).length()) answer += (value+1);
            else answer += value;
        }
        
        value = overlapLen(list.get(words.length-2), list.get(words.length-1));
        
        if(value<list.get(words.length-1).length()) answer += (value+1);
        else answer += value;
        
        return answer;
    }
    
    public int overlapLen(String s1, String s2) {
        int len = 0;
        for(int i=0;i<s1.length() && i<s2.length();i++) {
            if(s1.charAt(i) == s2.charAt(i)) len++;
            else break;
        }
        return len;
    }
}