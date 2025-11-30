import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_해킹 {

    static int n, d, c, cnt, time;
    static List<Node>[] adjList;
    static int[] dist;

    static class Node implements Comparable<Node>{
        int to, weight;
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[n + 1];
            for (int i = 1; i < n + 1; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                adjList[from].add(new Node(to, weight));
            }

            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            cnt = 0;
            time = 0;

            dijkstra(c);

            sb.append(cnt + " " + time + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.to] < cur.weight) continue;

            for (Node next : adjList[cur.to]) {
                int distance = dist[cur.to] + next.weight;
                if (distance < dist[next.to]) {
                    dist[next.to] = distance;
                    pq.add(new Node(next.to, distance));
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                cnt++;
                time = Math.max(time, dist[i]);
            }
        }
    }
}