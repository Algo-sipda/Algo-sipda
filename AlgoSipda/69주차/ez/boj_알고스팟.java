import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_알고스팟 {
    static class Point implements Comparable<Point> {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][N];

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        PriorityQueue<Point> queue = new PriorityQueue<Point>();
        boolean[][] visited = new boolean[M][N];
        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };
        int res = 0;
        if (map[0][0] == 1) {
            queue.add(new Point(0, 0, 1));
        } else {
            queue.add(new Point(0, 0, 0));
        }

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.r == M - 1 && cur.c == N - 1) {
                res = cur.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int cnt = cur.cnt;

                if (nr < 0 || nc < 0 || nr >= M || nc >= N || visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                if (map[nr][nc] == 1) {
                    queue.add(new Point(nr, nc, cnt + 1));
                } else {
                    queue.add(new Point(nr, nc, cnt));
                }
            }
        }

        System.out.println(res);
    }
}
