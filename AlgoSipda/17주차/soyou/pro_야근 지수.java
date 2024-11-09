import java.io.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }

        while(n > 0) {
            int target = pq.poll();

            if(target == 0) break;

            pq.offer(target - 1);
            n--;
        }

        for(int i: pq) {
            answer += i * i;
        }

        return answer;
    }
}