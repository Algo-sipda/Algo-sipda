import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int N, M, fuel;

    static class Node implements Comparable<Node> {
        int r, c, cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost == o.cost) {
                if (this.r == o.r)
                    return this.c - o.c;
                return this.r - o.r;
            }
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        int[][] area = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Node[] destinations = new Node[M + 2];

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            area[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = i;
            destinations[i] = new Node(Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1, 0);
        }

        int success = 0;

        while (fuel > 0 && success < M) {
            // 1. 승객 찾기
            PriorityQueue<Node> pq = new PriorityQueue<>();
            boolean[][] visited = new boolean[N][N];
            pq.offer(new Node(sr, sc, 0));
            visited[sr][sc] = true;
            Node passenger = new Node(-1, -1, 0);

            Top: while (!pq.isEmpty()) {
                Node curr = pq.poll();

                if (fuel <= curr.cost) {
                    System.out.println(-1);
                    return;
                }

                if (area[curr.r][curr.c] > 1) {
                    passenger.r = curr.r;
                    passenger.c = curr.c;
                    fuel -= curr.cost;

                    break Top;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dr[d];
                    int nc = curr.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]
                            || area[nr][nc] == 1) {
                        continue;
                    }

                    pq.offer(new Node(nr, nc, curr.cost + 1));
                    visited[nr][nc] = true;

                }
            }

            // 승객 못 찾으면
            if (passenger.r == -1) {
                System.out.println(-1);
                return;
            }

            for (boolean[] row : visited) {
                Arrays.fill(row, false);
            }

            // 2. 데려다주기
            Queue<Node> q = new ArrayDeque<>();
            q.offer(new Node(passenger.r, passenger.c, 0));
            Node destination = destinations[area[passenger.r][passenger.c]];
            area[passenger.r][passenger.c] = 0;
            visited[passenger.r][passenger.c] = true;

            Top: while (!q.isEmpty()) {
                Node curr = q.poll();

                if (fuel < curr.cost) {
                    System.out.println(-1);
                    return;
                }

                if (curr.r == destination.r && curr.c == destination.c) {
                    sr = curr.r;
                    sc = curr.c;
                    fuel += curr.cost;
                    success++;
                    break Top;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dr[d];
                    int nc = curr.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]
                            || area[nr][nc] == 1) {
                        continue;
                    }

                    q.offer(new Node(nr, nc, curr.cost + 1));
                    visited[nr][nc] = true;
                }
            }
        }

        System.out.println(fuel);
    }
}
