class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        int[][] dp = new int[2][money.length];
        
        dp[0][0] = dp[0][1] = money[0];
        dp[1][0] = 0;
        dp[1][1] = money[1];
        
        for(int i=2;i<money.length;i++) {
            dp[0][i] = Math.max(dp[0][i-1], money[i] + dp[0][i-2]);
            dp[1][i] = Math.max(dp[1][i-1], money[i] + dp[1][i-2]);
        }
        
        return Math.max(dp[0][money.length-2], dp[1][money.length-1]);
    }
}