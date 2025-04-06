class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int n = money.length;
        int[] dp = new int[n];
                
        // 첫번째 털었을 때
        dp[0] = money[0];
        dp[1] = dp[0];
        
        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        
        answer = dp[n - 2];
        
        // 안 털었을 때
        dp = new int[n];
        dp[1] = money[1];
        
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        
        return Math.max(answer, dp[n - 1]);
    }
}
