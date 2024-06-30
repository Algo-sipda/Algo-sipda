import java.util.*;

/*
    1. 앞에서부터 상자 계속 까보면서 카운트
        -> 최댓값 2개 저장
    2. 최댓값 2개 곱한 거 출력
*/

class Solution {

    static PriorityQueue<Integer> pq;

    public int solution(int[] cards) {
        int answer = 0;

        boolean[] visited = new boolean[cards.length];
        pq = new PriorityQueue<>((o1, o2) -> { return o2 - o1; });

        for (int i = 0; i < cards.length; i++) {
            int cnt = 0;
            int idx = i;
            while (true) {
                if (!visited[idx]) {
                    visited[idx] = true;
                    idx = cards[idx] - 1;
                    cnt++;
                    continue;
                }
                pq.add(cnt);
                break;
            }
        }

        if (pq.size() == 1) {
            return 0;
        }

        answer = pq.poll() * pq.poll();

        return answer;
    }

}