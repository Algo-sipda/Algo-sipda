import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_탈출 {
    static int R, C, time;
    static char[][] map;
    static Point S, D;
    static Queue<Point> waters;
    static boolean[][] visited;
    static final int[] dr = {1, -1, 0, 0};
    static final int[] dc = {0, 0, 1, -1};

    static class Point {
        int row, col;
        int cnt;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        waters = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    S = new Point(i, j, 0);
                    map[i][j] = '.';
                } else if (map[i][j] == 'D') {
                    D = new Point(i, j);
                } else if (map[i][j] == '*') {
                    waters.add(new Point(i, j));
                }
            }
        }

        time = -1;

        bfs();

        if (time == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(time);
        }
    }

    private static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(S);

        while (!queue.isEmpty()) {

            // 물이 참
            fillWater();

            for (int idx = 0; idx < queue.size(); idx++) {
                Point cur = queue.poll();
                if (map[cur.row][cur.col] == 'D') {
                    time = cur.cnt;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.row + dr[i];
                    int nc = cur.col + dc[i];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (map[nr][nc] == '*' || map[nr][nc] == 'X' || visited[nr][nc]) continue;

                    queue.add(new Point(nr, nc, cur.cnt + 1));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static void fillWater() {
        int len = waters.size();
        for (int idx = 0; idx < len; idx++) {
            Point cur = waters.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (map[nr][nc] == 'D' || map[nr][nc] == 'X' || map[nr][nc] == '*') continue;

                map[nr][nc] = '*';
                waters.add(new Point(nr, nc));
            }
        }
    }
}