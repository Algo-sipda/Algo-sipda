import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;

public class Main {
    static int N, M, A, B, C;
    static List<List<Edge>> g;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        g = new ArrayList<List<Edge>>();
        for (int i = 0; i <= N; i++) {
            g.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g.get(u).add(new Edge(v, w));
            g.get(v).add(new Edge(u, w));
        }

        long[] d1, d2, d3;
        d1 = dijkstra(A);
        d2 = dijkstra(B);
        d3 = dijkstra(C);

        int idx = 1;
        long best = -1;
        for (int i = 1; i <= N; i++) {
            long m = Math.min(d1[i], Math.min(d2[i], d3[i]));
            if (m > best || (m == best && i < idx)) {
                best = m;
                idx = i;
            }
        }
        System.out.println(idx);
    }

    static long[] dijkstra(int s) {
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Long.compare(a.d, b.d));
        dist[s] = 0;
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.x;
            long d = cur.d;
            if (d > dist[u])
                continue;
            for (Edge e : g.get(u)) {
                int v = e.to;
                long nd = d + e.w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new Node(v, nd));
                }
            }
        }
        return dist;
    }

    static class Edge {
        int to, w;
        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class Node {
        int x;
        long d;
        Node(int x, long d) {
            this.x = x;
            this.d = d;
        }
    }
}
