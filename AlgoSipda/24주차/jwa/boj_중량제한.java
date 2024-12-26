import java.io.*;
import java.util.*;

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
        HashMap<Integer, List<Node>> graph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
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
