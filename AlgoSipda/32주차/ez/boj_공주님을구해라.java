import java.util.*;
import java.io.*;

public class boj_공주님을구해라 {

    static int N, M, T;
    static int[][] map;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};

    static class Point {
        int r, c, cnt;
        boolean sword;
        public Point(int r, int c, int cnt, boolean sword) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.sword = sword;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = bfs(1, 1);

        if (res == -1) {
            System.out.println("Fail");
        } else {
            System.out.println(res);
        }
    }

    private static int bfs(int r, int c) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N + 1][M + 1][2];
        queue.add(new Point(r, c, 0, false));
        visited[r][c][0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.cnt > T) {
                return -1;
            }
            if (cur.r == N && cur.c == M) {
                return cur.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (isOut(nr, nc) || (!cur.sword && map[nr][nc] == 1)) continue;
                if ((!cur.sword && visited[nr][nc][0]) || (cur.sword && visited[nr][nc][1])) continue;
                if (map[nr][nc] == 2) {
                    cur.sword = true;
                }
                if (cur.sword) {
                    visited[nr][nc][1] = true;
                } else {
                    visited[nr][nc][0] = true;
                }
                queue.add(new Point(nr, nc, cur.cnt + 1, cur.sword));
            }
        }

        return -1;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > M;
    }
}
