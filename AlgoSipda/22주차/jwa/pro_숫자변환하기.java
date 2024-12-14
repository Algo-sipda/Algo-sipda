import java.util.*;

class Solution {
    static int MAX_VALUE = 1_000_000;

    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];
        Arrays.fill(dp, MAX_VALUE);
        dp[x] = 0;

        for (int i = x; i <= y; i++) {
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (i - n >= x) {
                dp[i] = Math.min(dp[i], dp[i - n] + 1);
            }
        }

        if (dp[y] == MAX_VALUE) {
            return -1;
        }

        return dp[y];
    }
}
