import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        int k;
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
        }

        int[] fixed = new int[n + 1];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int kind = Integer.parseInt(st.nextToken());
            fixed[day] = kind;
        }

        int[][][] dp = new int[n + 1][4][3];

        if (fixed[1] == 0) {
            for (int p = 1; p <= 3; p++) {
                dp[1][p][1] = 1;
            }
        } else {
            dp[1][fixed[1]][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int cur = 1; cur <= 3; cur++) {
                if (fixed[i] != 0 && cur != fixed[i])
                    continue;
                for (int prev = 1; prev <= 3; prev++) {
                    for (int c = 1; c <= 2; c++) {
                        int val = dp[i - 1][prev][c];
                        if (val == 0)
                            continue;
                        if (cur == prev) {
                            if (c < 2) {
                                dp[i][cur][c + 1] += val;
                                if (dp[i][cur][c + 1] >= MOD) dp[i][cur][c + 1] -= MOD;
                            }
                        } else {
                            dp[i][cur][1] += val;
                            if (dp[i][cur][1] >= MOD)
                                dp[i][cur][1] -= MOD;
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int p = 1; p <= 3; p++) {
            for (int c = 1; c <= 2; c++) {
                ans += dp[n][p][c];
                if (ans >= MOD)
                    ans -= MOD;
            }
        }
        System.out.println(ans);
    }
}
