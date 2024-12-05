import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;

    static class Node implements Comparable<Node> {
        int num, time;

        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Map<Integer, List<Node>> graph = new HashMap<>();
        Map<Integer, List<Node>> reversedGraph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
            reversedGraph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, T));
            reversedGraph.get(B).add(new Node(A, T));
        }

        int[] fromX = dijkstra(graph, X);
        int[] toX = dijkstra(reversedGraph, X);

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, fromX[i] + toX[i]);
        }

        System.out.println(answer);
    }

    public static int[] dijkstra(Map<Integer, List<Node>> graph, int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (distance[curr.num] < curr.time)
                continue;
            if (!graph.containsKey(curr.num))
                continue;
            for (Node next : graph.get(curr.num)) {
                int cost = distance[curr.num] + next.time;
                if (distance[next.num] < cost)
                    continue;
                distance[next.num] = cost;
                queue.add(new Node(next.num, cost));
            }
        }

        return distance;
    }
}
