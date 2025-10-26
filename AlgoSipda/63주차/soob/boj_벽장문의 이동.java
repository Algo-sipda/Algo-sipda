import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] req;
    static int[][][] dp;

    static int solve(int idx, int l, int r) {
        if (idx == req.length)
            return 0;
        if (dp[idx][l][r] != -1)
            return dp[idx][l][r];
        int t = req[idx];
        int moveL = Math.abs(l - t) + solve(idx + 1, t, r);
        int moveR = Math.abs(r - t) + solve(idx + 1, l, t);
        dp[idx][l][r] = Math.min(moveL, moveR);
        return dp[idx][l][r];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        req = new int[m];
        for (int i = 0; i < m; i++) {
            req[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[m + 1][n + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int x = 1; x <= n; x++) {
                Arrays.fill(dp[i][x], -1);
            }
        }
        System.out.println(solve(0, a, b));
    }
}
