import java.util.*;

/*
    - 누구에게 무적권을 사용해야 하는가(그리디)
    -> 병사 수가 많을 때 무적권 사용 / 적을 때 직접 막기
    1. 일단 무적권 사용
    2. 무적권이 없으면
        pq.poll() 명의 적은 직접 막기
*/

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < enemy.length; i++) {
            if (k > 0) {
                pq.add(enemy[i]);
                k--;
            } else {
                pq.add(enemy[i]);
                int num = pq.peek();
                if (n - num > -1) {
                    n -= pq.poll();
                } else {
                    break;
                }
            }
            answer = i + 1;
        }

        return answer;
    }
}