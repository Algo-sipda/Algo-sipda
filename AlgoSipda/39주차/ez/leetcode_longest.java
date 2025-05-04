import java.util.*;

class leetcode_longest {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char r = s.charAt(right);
            if (!set.contains(r)) {
                set.add(r);
                max = Math.max(max, right - left + 1);
            } else {
                while (set.contains(r)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(r);
            }
        }

        return max;
    }
}