class Solution {
    final int MOD = 1_000_000_007;
    
    public int solution(int n) {
        int[] dp = new int[100001];
        
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;
        
        for(int i = 7; i <= n; i++) {
            long val = (dp[i - 1] + 2L * dp[i - 2] + 6L * dp[i - 3] + dp[i - 4] - dp[i - 6]) % MOD;
            dp[i] = (int)((val + MOD) % MOD);
        }
        
        return dp[n];
    }
}
