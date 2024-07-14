import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> scov = new PriorityQueue<>();
        for(int i : scoville){
            scov.add(i);
        }
        
        int min = scov.peek();
        int answer = 0;
        
        while(scov.peek() < K && scov.size() >= 2){
            answer++;
            int min1 = scov.poll();
            int min2 = scov.poll();
            min = min1 + (min2 * 2);
            scov.add(min);
        }
        
        if(scov.peek() < K)
            answer = -1;
        
        return answer;
    }
}