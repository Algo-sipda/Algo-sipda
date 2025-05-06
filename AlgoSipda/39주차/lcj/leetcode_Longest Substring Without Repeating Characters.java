import java.util.*;

/*
    중복 없이 문자열 최대 길이
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();

        int answer = 0;
        int idx = 0, prev = 0;
        Set<Character> set = new HashSet<>();

        while(idx < s.length()) {
            char c = s.charAt(idx);

            if(!set.contains(c)) {
                set.add(c);
                idx++;
                answer = Math.max(answer, set.size());
            } else {
                set.remove(s.charAt(prev));
                prev++;
            }
        }


        return answer;
        
    }
}