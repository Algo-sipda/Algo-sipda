import java.util.*;

class Solution {
    int[][] dp;

    public int[] solution(int target) {
        dp = new int[target + 1][2];

        for (int i = 0; i <= target; i++) 
            dp[i] = new int[]{10000000, -10000000};

        dp[0][0] = 0;
        dp[0][1] = 0;

        for (int i = 0; i < target; i++) {
            for (int s = 1; s <= 20; s++) {
                if (i + s > target) break;
                update(i, i + s, 1, 1);
            }

            for (int d = 2; d <= 40; d += 2) {
                if (i + d > target) break;
                update(i, i + d, 1, 0);
            }

            for (int t = 3; t <= 60; t += 3) {
                if (i + t > target) break;
                update(i, i + t, 1, 0);
            }

            if (i + 50 <= target) 
                update(i, i + 50, 1, 1);
        }

        return dp[target];
    }

    private void update(int from, int to, int dartInc, int doubleInc) {
        if (dp[from][0] + dartInc < dp[to][0]) {
            dp[to][0] = dp[from][0] + dartInc;
            dp[to][1] = dp[from][1] + doubleInc;
        } else if (dp[from][0] + dartInc == dp[to][0] && dp[from][1] + doubleInc > dp[to][1]) {
            dp[to][1] = dp[from][1] + doubleInc;
        }
    }
}
