import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2).replace("},{", "-");
        String[] parts = s.split("-");
        Arrays.sort(parts, Comparator.comparingInt(String::length));
        
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        
        for (String part : parts) {
            String[] nums = part.split(",");
            for (String num : nums) {
                int n = Integer.parseInt(num);
                if (set.add(n)) result.add(n);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}
