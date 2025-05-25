import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<int[]>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int[] r : road) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0], cost = cur[1];
            if (dist[now] < cost) continue;
            for (int[] next : graph[now]) {
                int to = next[0], time = next[1];
                if (dist[to] > dist[now] + time) {
                    dist[to] = dist[now] + time;
                    pq.add(new int[]{to, dist[to]});
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) count++;
        }
        return count;
    }
}
