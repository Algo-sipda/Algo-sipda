import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
            map[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int j = 0; j <= N; j++) {
            if (j != 1 && j != v1 && j != v2)
                continue;
            boolean[] visited = new boolean[N + 1];
            dist[j][j] = 0;
            PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.y));
            pq.add(new Point(j, 0));
            while (!pq.isEmpty()) {
                Point current = pq.poll();
                if (visited[current.x]) continue;
                visited[current.x] = true;
                for (int i = 1; i <= N; i++) {
                    if (map[current.x][i] == Integer.MAX_VALUE) continue;
                    if (map[current.x][i] + current.y < dist[j][i]) {
                        dist[j][i] = map[current.x][i] + current.y;
                        pq.add(new Point(i, dist[j][i]));
                    }
                }
            }
        }

        int d1 = 0;
        int d2 = 0;
        if (dist[1][v1] == Integer.MAX_VALUE || dist[v1][v2] == Integer.MAX_VALUE || dist[v2][N] == Integer.MAX_VALUE)
            d1 = Integer.MAX_VALUE;
        else
            d1 = dist[1][v1] + dist[v1][v2] + dist[v2][N];

        if (dist[1][v2] == Integer.MAX_VALUE || dist[v2][v1] == Integer.MAX_VALUE || dist[v1][N] == Integer.MAX_VALUE)
            d2 = Integer.MAX_VALUE;
        else
            d2 = dist[1][v2] + dist[v2][v1] + dist[v1][N];

        int answer = Math.min(d1, d2);
        if (answer == Integer.MAX_VALUE)
            answer = -1;
        System.out.println(answer);
    }
}