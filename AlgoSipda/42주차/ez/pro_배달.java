import java.util.*;

class pro_배달 {

    class Node implements Comparable<Node> {
        int v, dis;
        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        List<Node>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < road.length; i++) {
            adjList[road[i][0]].add(new Node(road[i][1], road[i][2]));
            adjList[road[i][1]].add(new Node(road[i][0], road[i][2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.v]) continue;
            visited[cur.v] = true;
            for (Node next : adjList[cur.v]) {
                if (!visited[next.v] && dist[next.v] > dist[cur.v] + next.dis) {
                    dist[next.v] = dist[cur.v] + next.dis;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}