import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int[][] area;
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        area = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, area[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
                continue;

            visited[nx][ny] = true;

            if (cnt == 2) {
                dfs(x, y, cnt + 1, sum + area[nx][ny]);
            }

            dfs(nx, ny, cnt + 1, sum + area[nx][ny]);

            visited[nx][ny] = false;
        }
    }
}
