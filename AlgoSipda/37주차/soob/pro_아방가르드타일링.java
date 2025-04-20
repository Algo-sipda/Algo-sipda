class Solution {

    public int solution(int n) {
        int[] dp = new int[100001];
        int MOD = 1_000_000_007;

        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;

        for (int i = 7; i <= n; i++) {
            long val = dp[i - 1];
            val += 2L * dp[i - 2];
            val += 6L * dp[i - 3];
            val += dp[i - 4];
            val -= dp[i - 6];
            dp[i] = (int)((val % MOD + MOD) % MOD);
        }

        return dp[n];
    }
}
