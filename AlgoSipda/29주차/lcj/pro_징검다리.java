import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = Integer.MIN_VALUE;
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            int cnt = 0;
            int prev = 0;
            
            for(int i=0;i<rocks.length;i++) {
                if(rocks[i] - prev < mid) cnt++;
                else prev = rocks[i];
                
                if(cnt > n) break;
            }
            
            if(distance - prev < mid && cnt <= n) cnt++;
            if(cnt > n) right = mid -1;
            else {
                left = mid + 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}