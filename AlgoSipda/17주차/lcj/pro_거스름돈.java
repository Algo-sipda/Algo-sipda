class Solution {
    final int MOD = 1_000_000_007;
    public int solution(int n, int[] money) {
        int[][] dp = new int[money.length+1][n+1];
        
        for(int i=0;i<=n;i++) {
            if(i % money[0] == 0) {
                dp[1][i] = 1;
            }
        }
        
        for(int i=2; i<=money.length;i++) {
            for(int j=0;j<=n;j++) {
                if(j==0) {
                    dp[i][j] = 1;
                }
                else if(j-money[i-1] >= 0) {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-money[i-1]])%MOD;
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[money.length][n];
    }
}