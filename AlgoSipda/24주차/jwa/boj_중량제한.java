import java.io.*;
import java.util.*;

// (1) 다익스트라 풀이
public class Main {
    static class Node {
        int n, c;

        Node(int n, int c) {
            this.n = n;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[] distance = new int[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.c - o1.c);
        pq.offer(new Node(S, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.c < distance[curr.n]) {
                continue;
            }

            for (Node adj : graph.get(curr.n)) {
                int cost = Math.min(curr.c, adj.c);
                if (cost <= distance[adj.n]) {
                    continue;
                }
                distance[adj.n] = cost;
                pq.add(new Node(adj.n, cost));
            }
        }

        System.out.println(distance[E]);
    }
}

// (2) 이분탐색 풀이
public class Main {
    static final int MAX_WEIGHT = 1_000_000_000;
    static List<List<Node>> graph;
    static int S, E;

    static class Node {
        int n, c;

        Node(int n, int c) {
            this.n = n;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int left = 0, right = MAX_WEIGHT;

        while (left <= right) {
            int mid = (left + right) / 2;
            boolean[] visited = new boolean[N + 1];

            if (bfs(mid, visited)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    static boolean bfs(int weight, boolean[] visited) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(S);
        visited[S] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (Node adj : graph.get(curr)) {
                if (visited[adj.n] || adj.c < weight) {
                    continue;
                }
                if (adj.n == E) {
                    return true;
                }
                q.offer(adj.n);
                visited[adj.n] = true;
            }
        }

        return false;
    }
}
