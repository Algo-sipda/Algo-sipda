import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<works.length;i++) {
            pq.add(works[i]);
        }
        
        while(n>0) {
            int cur = pq.poll();
            
            if(cur <= 0) break;
            
            cur -= 1;
            pq.add(cur);
            n--;
        }
        
        long answer = 0;
        for(int num:pq) {
            answer += Math.pow(num, 2);
        }
        
        return answer;
    }
}