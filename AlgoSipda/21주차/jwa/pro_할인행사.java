import java.util.*;

class Solution {
    static int PERIOD = 10;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> count = new HashMap<>();
        
        int start = 0, end = PERIOD - 1;
        
        for (int i = start; i <= end; i++) {
            String item = discount[i];
            if (count.containsKey(item)) {
                count.put(item, count.get(item) + 1);
            } else {
                count.put(item, 1);
            }
        }
        
        while (true) {
            boolean check = true;
            for (int i = 0; i < want.length; i++) {
                if (count.getOrDefault(want[i], 0) < number[i]) {
                    check = false;
                    break;
                }
            }
            
            if (check) {
                answer++;
            }
            count.put(discount[start], count.get(discount[start]) - 1);
            start += 1;
            end += 1;
            if (end == discount.length) {
                break;
            }
            if (count.containsKey(discount[end])) {
                count.put(discount[end], count.get(discount[end]) + 1);
            } else {
                count.put(discount[end], 1);
            }
        }
        
        return answer;
    }
}

// 슬라이딩 윈도우
