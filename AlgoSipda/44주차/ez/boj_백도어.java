import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_백도어 {

    static int N, M;
    static List<Node>[] adjList;
    static int[] state;

    static class Node implements Comparable<Node> {
        int to;
        long time;
        public Node(int to, long time) {
            this.to = to;
            this.time = time;
        }
        public int compareTo(Node o) {
            return (int) (this.time - o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        state = new int[N];
        adjList = new ArrayList[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            state[i] = Integer.parseInt(st.nextToken());
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, t));
            adjList[b].add(new Node(a, t));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        pq.add(new Node(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            for (Node next : adjList[cur.to]) {
                if (next.to != N - 1 && state[next.to] == 1) continue;
                if (visited[next.to]) continue;
                if (dist[next.to] > dist[cur.to] + next.time) {
                    dist[next.to] = dist[cur.to] + next.time;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        if (dist[N - 1] == Long.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(dist[N - 1]);
    }
}
