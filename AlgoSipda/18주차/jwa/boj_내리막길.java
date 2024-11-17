import java.io.*;
import java.util.*;

public class boj_내리막길 {
    static int N, M;
    static int[][] area;
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] dp;

    static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1) {
            return 1;
        }
        if (dp[x][y] > -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                continue;
            if (area[x][y] <= area[nx][ny])
                continue;
            dp[x][y] += dfs(nx, ny);
        }

        return dp[x][y];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        area = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }
}
