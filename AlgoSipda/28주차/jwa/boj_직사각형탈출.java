import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {-1, 1, 0, 0};

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] area = new int[N + 1][M + 1];
        boolean[][] visited = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                area[i][j] += area[i - 1][j] + area[i][j - 1] - area[i - 1][j - 1];
            }
        }

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] start = new int[2];
        int[] finish = new int[2];
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());
        finish[0] = Integer.parseInt(st.nextToken());
        finish[1] = Integer.parseInt(st.nextToken());

        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(start[0], start[1]));
        visited[start[0]][start[1]] = true;

        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Point curr = q.poll();

                if (curr.x == finish[0] && curr.y == finish[1]) {
                    System.out.println(level);
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];
                    int nh = nx + H - 1;
                    int nw = ny + W - 1;

                    if (nx <= 0 || nx > N || ny <= 0 || ny > M || nh <= 0 || nh > N || nw <= 0
                            || nw > M || visited[nx][ny]) {
                        continue;
                    }

                    int count = area[nh][nw] - area[nh][ny - 1] - area[nx - 1][nw]
                            + area[nx - 1][ny - 1];

                    if (count != 0) {
                        continue;
                    }

                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            level++;
        }

        System.out.println(-1);
    }
}
