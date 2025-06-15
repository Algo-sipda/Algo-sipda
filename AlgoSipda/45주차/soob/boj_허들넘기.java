import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to, maxH;

        Node(int to, int maxH) {
            this.to = to;
            this.maxH = maxH;
        }

        public int compareTo(Node o) {
            return this.maxH - o.maxH;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, h)); // 단방향
        }

        StringBuilder sb = new StringBuilder();
        Map<Integer, int[]> memo = new HashMap<>();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (!memo.containsKey(s)) {
                int[] dist = new int[N + 1];
                Arrays.fill(dist, Integer.MAX_VALUE);
                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.offer(new Node(s, 0));
                dist[s] = 0;

                while (!pq.isEmpty()) {
                    Node curr = pq.poll();
                    if (curr.maxH > dist[curr.to]) continue;

                    for (Node next : graph[curr.to]) {
                        int cost = Math.max(curr.maxH, next.maxH);
                        if (dist[next.to] > cost) {
                            dist[next.to] = cost;
                            pq.offer(new Node(next.to, cost));
                        }
                    }
                }

                memo.put(s, dist);
            }

            int[] dist = memo.get(s);
            sb.append(dist[e] == Integer.MAX_VALUE ? -1 : dist[e]).append("\n");
        }

        System.out.print(sb);
    }
}
