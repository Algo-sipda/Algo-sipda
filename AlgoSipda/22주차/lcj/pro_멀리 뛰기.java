/*
    멀리뛰기 연습
    한번에 1칸 or 2칸
    
    1칸: 1 (1)
    2칸: 1 1 / 2 (2)
    3칸: 1 1 1 / 1 2 / 2 1 (3)
    4칸: 1 1 1 1 / 1 1 2 / 1 2 1 / 2 1 1 / 2 2 (5)
*/

class Solution {
    int MOD = 1234567;
    
    public long solution(int n) {
        long[] dp = new long[2001];
        
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<dp.length;i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % MOD;
        }
        
        return dp[n];
    }
}