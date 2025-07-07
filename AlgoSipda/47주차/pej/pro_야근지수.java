// [PRO] 야근 지수 https://school.programmers.co.kr/learn/courses/30/lessons/12927

import java.util.*;
import java.io.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int w : works){
            pq.add(w);
        }
        while(n-- > 0){
            int x = pq.poll();
            if(x > 0){x -= 1;}
            else {x = 0;}
            pq.add(x);
        }
        while(!pq.isEmpty()){
            int x = pq.poll();
            answer += x*x;
        }
        return answer;
    }
}