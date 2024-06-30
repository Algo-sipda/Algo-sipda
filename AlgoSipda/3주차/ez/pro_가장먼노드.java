import java.util.*;

/*
    풀이 방식: 다익스트라
 */

class Solution {

    static class Node implements Comparable<Node> {
        int v, weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static List<Node> []adjList;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        adjList = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            adjList[from].add(new Node(to, 1));
            adjList[to].add(new Node(from, 1));
        }

        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.v]) continue;

            visited[cur.v] = true;

            for (Node next : adjList[cur.v]) {
                if (!visited[next.v] && dist[next.v] > dist[cur.v] + next.weight) {
                    dist[next.v] = dist[cur.v] + next.weight;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) continue;
            max = Math.max(max, dist[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] == max) {
                answer++;
            }
        }
        return answer;
    }
}