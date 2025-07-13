import java.util.*;

class Solution {

    int mod = 10007;

    public int solution(int n, int[] tops) {
        int[][] dp = new int[tops.length][4];
        
        Arrays.fill(dp[0], 1);
        if (tops[0] == 0) dp[0][3] = 0;

        for (int i = 1; i < tops.length; i++) {

            int total = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3]);

            dp[i][0] = total % mod;
            dp[i][1] = (total - dp[i-1][2]) % mod;
            dp[i][2] = total % mod;
            dp[i][3] = (tops[i] == 0) ? 0 : total;
        }

        int len = tops.length - 1;
        int answer = (dp[len][0] + dp[len][1] + dp[len][2] + dp[len][3]) % mod;

        return answer;
    }
}