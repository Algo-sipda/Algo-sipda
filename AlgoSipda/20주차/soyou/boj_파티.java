import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {

    int idx;
    int weight;

    public Node(int idx, int weight) {
        this.idx = idx;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder out = new StringBuilder();

    static int n, m, x;
    static int INF = Integer.MAX_VALUE;
    static List<Node>[] graph, reverseGraph;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        reverseGraph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
            reverseGraph[to].add(new Node(from, weight));
        }

        int result = 0;

        int[] dist = dijkstra(graph, x);
        int[] reverseDist = dijkstra(reverseGraph, x);
        for (int i = 0; i <= n; i++) {
            result = Math.max(result, dist[i] + reverseDist[i]);
        }

        System.out.println(result);
    }

    public static int[] dijkstra(List<Node>[] graph, int start) {
        int[] dist = new int[n + 1];
        boolean[] check = new boolean[n + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int current = pq.poll().idx;

            if(check[current]) continue;
            check[current] = true;

            for(Node next: graph[current]) {
                if(dist[next.idx] > dist[current] + next.weight) {
                    dist[next.idx] = dist[current] + next.weight;

                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return dist;
    }
}
