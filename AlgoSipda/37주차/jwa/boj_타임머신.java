import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[M];
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(u, v, w);
        }

        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.from] != Long.MAX_VALUE && dist[e.to] > dist[e.from] + e.cost) {
                    dist[e.to] = dist[e.from] + e.cost;
                }
            }
        }

        for (Edge e : edges) {
            if (dist[e.from] != Long.MAX_VALUE && dist[e.to] > dist[e.from] + e.cost) {
                System.out.println("-1");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (dist[i] == Long.MAX_VALUE) {
                sb.append("-1\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
}
