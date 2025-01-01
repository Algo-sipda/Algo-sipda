import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 섬 개수
        int M = Integer.parseInt(st.nextToken());   // 다리 수
        Map<Integer, ArrayList<Edge>> map = new HashMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(A, key -> new ArrayList<>());
            map.computeIfAbsent(B, key -> new ArrayList<>());

            updateEdge(map.get(A), B, W);
            updateEdge(map.get(B), A, W);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight));
        int[] limitArr = new int[N + 1];
        limitArr[s] = Integer.MAX_VALUE;
        pq.add(new Edge(s, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (now.to == e) {
                System.out.println(limitArr[e]);
                return;
            }

            for (Edge next : map.get(now.to)) {
                int limit = Math.min(now.weight, next.weight);
                if (limitArr[next.to] < limit) {
                    limitArr[next.to] = limit;
                    pq.add(new Edge(next.to, limit));
                }
            }
        }
    }

    private static void updateEdge(ArrayList<Edge> list, int target, int weight) {
        for (Edge edge : list) {
            if (edge.to == target) {
                if (edge.weight < weight) {
                    edge.weight = weight;
                }
                return;
            }
        }
        list.add(new Edge(target, weight));
    }
}