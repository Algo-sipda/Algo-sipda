// [LEETCODE] longest-substring-without-repeating-characters 
// https://leetcode.com/problems/longest-substring-without-repeating-characters

import java.util.*;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 1;

        int N = s.length();
        Map<Character, Integer> map = new HashMap<>();
        // char을 어느 위치에서 확인했었는지 
        if(N == 0){
            res = 0;
            return res;
        }
        int i = 0, j = 0;
        while(j < N) {
            // 현재 확인 범위(i이상 j이하)에 나온적이 없었는지  
            if(map.containsKey(s.charAt(j)) && 
                map.get(s.charAt(j)) >= i
            ) {
                i = map.get(s.charAt(j)) + 1;
            }else { 
                res = Math.max(res, j - i + 1);
            }
            map.put(s.charAt(j), j);
            j++;
        }

        return res;

    }
}