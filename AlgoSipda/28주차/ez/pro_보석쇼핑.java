import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

class pro_보석쇼핑 {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>(Arrays.asList(gems));
        int kind = set.size();
        int len = Integer.MAX_VALUE;
        HashMap<String, Integer> map = new HashMap<>();
        int start = 0, end = 0;

        while(true) {

            if(end == gems.length) break;

            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            end++;

            if(map.get(gems[start]) > 1) {
                map.put(gems[start], map.getOrDefault(gems[start], 0) - 1);
                start++;
            }
            if(kind == map.size() && len > Math.abs(end-start)) {
                len = Math.abs(end - start);
                answer[0] = start + 1;
                answer[1] = end;
            }
        }

        return answer;
    }
}