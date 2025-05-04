class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if(map.containsKey(c)){
                    map.put(c, j);
                    break;
                }
                map.put(c, j);
                max = Math.max(max, j - i + 1);
            }
        }

        return max;
    }
}