class Solution {
    public int solution(int n) {
        int MOD = 1_000_000_007;
        long[] dp = new long[100001];

        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;

        for (int i = 7; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2 + dp[i - 3] * 6 + dp[i - 4] - dp[i - 6]) % MOD;

            if (dp[i] < 0)
                dp[i] += MOD;
        }

        return (int) dp[n];
    }
}
