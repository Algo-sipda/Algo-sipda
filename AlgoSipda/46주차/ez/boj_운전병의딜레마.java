import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_운전병의딜레마 {

    static int N, M, T;
    static List<Node>[] adjList;

    static class Node implements Comparable<Node> {
        int to, s;
        long t;
        public Node(int to, long t, int s) {
            this.to = to;
            this.t = t;
            this.s = s;
        }
        public int compareTo(Node o) {
            return Long.compare(this.t, o.t);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        int maxStress = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, t, s));
            adjList[to].add(new Node(from, t, s));
            maxStress = Math.max(maxStress, s);
        }

        int start = 0;
        int end = maxStress;
        long result = Long.MAX_VALUE;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (dijkstra(mid)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (result == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

    private static boolean dijkstra(int stress) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        pq.add(new Node(1, 0, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.to] < cur.t) continue;
            if (cur.to == N) {
                return true;
            }

            for (Node next : adjList[cur.to]) {
                if (next.s > stress) {
                    next.t += next.s - stress;
                    next.s = stress;
                }
                long nextDistance = cur.t + next.t;
                if (nextDistance <= T && nextDistance < dist[next.to]) {
                    dist[next.to] = nextDistance;
                    pq.add(new Node(next.to, nextDistance, next.s));
                }
            }
        }
        return false;
    }
}
