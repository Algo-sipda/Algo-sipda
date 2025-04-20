import java.io.*;
import java.util.*;

public class boj_타임머신 {

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Edge[] edgeList = new Edge[M + 1];
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(A, B, C);
        }

        dist[1] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edgeList[j];
                if (dist[edge.from] != Integer.MAX_VALUE && dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        boolean check = false;
        for (int i = 0; i < M; i++) {
            Edge edge = edgeList[i];
            if(dist[edge.from] != Integer.MAX_VALUE && dist[edge.from] + edge.cost < dist[edge.to]){
                check = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (check) {
            sb.append("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    sb.append("-1\n");
                } else {
                    sb.append(dist[i] + "\n");
                }
            }
        }
        System.out.println(sb);
    }
}
