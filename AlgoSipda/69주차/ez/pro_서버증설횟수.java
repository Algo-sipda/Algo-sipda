import java.util.*;

class pro_서버증설횟수 {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int cnt = 0; // 현재 서버 수
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 서버 개수, 시간대

        for (int i = 0; i < players.length; i++) {

            while (!pq.isEmpty()) {
                if (pq.peek()[1] <= i) {
                    cnt -= pq.poll()[0];
                    continue;
                }
                break;
            }
            int server = players[i] / m;
            if (server > cnt) {
                int up = server - cnt;
                answer += up;
                cnt = server;
                pq.add(new int[] {up, i + k});
            }
        }

        return answer;
    }
}