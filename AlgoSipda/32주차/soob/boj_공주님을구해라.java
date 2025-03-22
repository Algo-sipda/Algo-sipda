import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 성의 세로 크기
        int M = Integer.parseInt(st.nextToken());   // 성의 가로 크기
        int T = Integer.parseInt(st.nextToken());   // 제한 시간
        int[][] map = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];  // 0: 검 없음, 1: 검 있음
        queue.offer(new Point(0, 0, 0, false));
        visited[0][0][0] = true;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.time > T) continue;
            if (now.y == N - 1 && now.x == M - 1)
                answer = Math.min(answer, now.time);

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (visited[ny][nx][now.sword ? 1 : 0]) continue;
                if (map[ny][nx] == 1) {
                    if (!now.sword) continue;
                }
                visited[ny][nx][now.sword ? 1 : 0] = true;
                if (map[ny][nx] == 2) {
                    queue.offer(new Point(ny, nx, now.time + 1, true));
                } else {
                    queue.offer(new Point(ny, nx, now.time + 1, now.sword));
                }
            }
        }

        if (answer > T) System.out.println("Fail");
        else System.out.println(answer);
    }

    public static class Point {
        int y, x, time;
        boolean sword;

        public Point(int y, int x, int time, boolean sword) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.sword = sword;
        }
    }
}
