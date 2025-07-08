import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static int[] dy = {1, 0, -1, 0};

    static class Dice {
        int up = 1, down = 6, north = 2, south = 5, east = 3, west = 4;

        void roll(int dir) {
            int tmp;
            if (dir == 0) {
                tmp = up;
                up = west;
                west = down;
                down = east;
                east = tmp;
            } else if (dir == 1) {
                tmp = up;
                up = north;
                north = down;
                down = south;
                south = tmp;
            } else if (dir == 2) {
                tmp = up;
                up = east;
                east = down;
                down = west;
                west = tmp;
            } else {
                tmp = up;
                up = south;
                south = down;
                down = north;
                north = tmp;
            }
        }
    }

    static int bfs(int x, int y, int val) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == val) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }
        return cnt * val;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = 0, y = 0, dir = 0, score = 0;
        Dice dice = new Dice();

        for (int i = 0; i < K; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                dir = (dir + 2) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            dice.roll(dir);
            x = nx;
            y = ny;
            int B = map[x][y];
            score += bfs(x, y, B);
            int A = dice.down;
            if (A > B) dir = (dir + 1) % 4;
            else if (A < B) dir = (dir + 3) % 4;
        }

        System.out.println(score);
    }
}
