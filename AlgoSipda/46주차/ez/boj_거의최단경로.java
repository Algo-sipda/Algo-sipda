import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 최단 경로 찾기(다익스트라)
    - 이때 최단 경로 저장하기 - removeList
    - 최단 경로가 여러 개일 수 있으니 list 사용
2. 최단 경로 지우기
    - boolean 이차원 배열
3. 다익스트라 다시
 */

public class boj_거의최단경로 {

    static int N, M, S, D;
    static List<Node>[] adjList;
    static List<Integer>[] removeList;
    static boolean[][] visited;
    static int[] dist;

    static class Node implements Comparable<Node> {
        int to, w;
        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[N];
            removeList = new ArrayList[N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<>();
                removeList[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adjList[from].add(new Node(to, w));
            }

            dijkstra();
            removeRoad(D);
            dijkstra();

            if (dist[D] == Integer.MAX_VALUE) {
                sb.append(-1);
            } else {
                sb.append(dist[D]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void removeRoad(int cur) {
        if (S == cur) {
            return;
        }

        for (int next : removeList[cur]) {
            if (!visited[next][cur]) {
                visited[next][cur] = true;
                removeRoad(next);
            }
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.w > dist[cur.to]) continue;
            for (Node next : adjList[cur.to]) {
                if (visited[cur.to][next.to]) continue;
                if (dist[next.to] > dist[cur.to] + next.w) {
                    dist[next.to] = dist[cur.to] + next.w;
                    removeList[next.to] = new ArrayList<>();
                    removeList[next.to].add(cur.to);
                    pq.add(new Node(next.to, dist[next.to]));
                } else if (dist[next.to] == dist[cur.to] + next.w) {
                    removeList[next.to].add(cur.to);
                }
            }
        }
    }
}
