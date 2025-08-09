import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                W[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][1<<N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        System.out.println(tsp(0, 1));
    }

    static int tsp(int now, int mask) {
        if (mask == (1<<N)-1) return W[now][0] == 0 ? INF : W[now][0];
        if (dp[now][mask] != -1) return dp[now][mask];
        int res = INF;
        for (int next = 0; next < N; next++) {
            if ((mask & (1<<next)) != 0) continue;
            if (W[now][next] == 0) continue;
            int cand = W[now][next] + tsp(next, mask | (1<<next));
            if (cand < res) res = cand;
        }
        return dp[now][mask] = res;
    }
}
