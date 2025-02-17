import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_테트로미노 {

    static int N, M, max;
    static int[][] map;
    static boolean[][] visited;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(0, i, j, 0);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void dfs(int cnt, int r, int c, int sum) {
        if (cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (isOut(nr, nc) || visited[nr][nc]) continue;

            if (cnt == 2) {
                visited[nr][nc] = true;
                dfs(cnt + 1, r, c, sum + map[nr][nc]);
                visited[nr][nc] = false;
            }

            visited[nr][nc] = true;
            dfs(cnt + 1, nr, nc, sum + map[nr][nc]);
            visited[nr][nc] = false;
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }
}
