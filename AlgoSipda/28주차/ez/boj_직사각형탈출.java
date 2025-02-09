import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_직사각형탈출 {

    static int N, M, H, W, min;
    static int[][] map, sum;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static Point start, end;

    static class Point {
        int r, c;
        public Point (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        sum = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        min = -1;

        bfs();

        System.out.println(min);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {start.r, start.c, 0});
        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[start.r][start.c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == end.r && cur[1] == end.c) {
                min = cur[2];
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || visited[nr][nc]) continue;
                if (sum[nr + H - 1][nc + W - 1] - sum[nr + H - 1][nc - 1] - sum[nr - 1][nc + W - 1] + sum[nr - 1][nc - 1] > 0) continue;
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc, cur[2] + 1});
            }
        }
    }

    private static boolean isOut(int nr, int nc) {
        if (nr < 1 || nc < 1 || nr > N || nc > M) {
            return true;
        }
        if (nr + H - 1 > N || nc + W  - 1 > M) {
            return true;
        }
        return false;
    }

    private static void printMap(int[][] map) {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
