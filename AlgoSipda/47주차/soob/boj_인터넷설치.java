import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int to, cost;
        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int n, m, k;
    static List<Node>[] graph;

    static boolean isPossible(int limit) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{1, 0});
        dist[1] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int cnt = cur[1];

            if (dist[now] < cnt) continue;

            for (Node next : graph[now]) {
                int nextCnt = cnt + (next.cost > limit ? 1 : 0);
                if (dist[next.to] > nextCnt) {
                    dist[next.to] = nextCnt;
                    pq.offer(new int[]{next.to, nextCnt});
                }
            }
        }

        return dist[n] <= k;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        int maxCost = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, c));
            graph[v].add(new Node(u, c));
            maxCost = Math.max(maxCost, c);
        }

        int left = 0, right = maxCost, answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
