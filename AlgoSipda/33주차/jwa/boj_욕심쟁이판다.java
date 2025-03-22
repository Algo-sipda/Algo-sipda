import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int N;
    static int[][] area, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        area = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N];

        int answer = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0)
                    answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    static int dfs(int r, int c) {
        if (dp[r][c] > 0) {
            return dp[r][c];
        }

        dp[r][c] = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || area[nr][nc] <= area[r][c]) {
                continue;
            }

            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }

        return dp[r][c];
    }
}
