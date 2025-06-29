import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        
        for(int work:works) {
            pq.add(work);
        }
        
        while(n>0) {
            if(pq.isEmpty()) break;
            
            int value = pq.poll();
            
            n--;
            value--;
            
            if(value > 0) pq.add(value);
        }
        
        long answer = 0;
        
        while(!pq.isEmpty()) {
            int t = pq.poll();
            answer += t*t;
        }
        
        return answer;
    }
}