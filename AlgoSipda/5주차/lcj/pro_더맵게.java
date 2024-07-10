import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<scoville.length;i++) {
            pq.add(scoville[i]);
        }
        
        int answer = 0;
        
        while(true) {
            if(pq.isEmpty()) break;
            
            int first = pq.poll();
            if(first<K) {
                if(pq.isEmpty()) return -1;
                int second = pq.poll();
                pq.add(first + second*2);
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
}