import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_욕심쟁이판다 {

    static int N;
    static int[][] map, dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    private static int dfs(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];
        dp[r][c] = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isOut(nr, nc) || map[r][c] >= map[nr][nc]) continue;
            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }

        return dp[r][c];
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}
