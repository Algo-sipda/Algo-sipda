class Solution {
    
    final int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        // 0 : 마름모 타일로 끝나지 않을 경우
        // 1 : 마름모 타일로 끝날 경우
        int[][] dp = new int[n][2];
        
        dp[0][0] = tops[0] == 1? 3:2;
        dp[0][1] = 1;
        
        for(int i=1;i<n;i++) {
            int m1 = tops[i] == 1? 3 : 2;
            int m2 = tops[i] == 1? 2 : 1;
            
            dp[i][0] = (dp[i-1][0] * m1 + dp[i-1][1] * m2) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % MOD;
        }
        
        return (dp[n-1][0] + dp[n-1][1]) % MOD;
    }
}
// 다시 풀어보기