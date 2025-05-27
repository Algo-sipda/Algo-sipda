import java.util.*;

class Solution {

    final int INF = 100001;

    public int solution(int[][] info, int n, int m) {
        int size = info.length;
        
        int [][] dp = new int [size+1][m];
        
        for(int i=0;i<dp.length;i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        
        for(int i=1;i<dp.length;i++) {
            int num1 = info[i-1][0];
            int num2 = info[i-1][1];
            
            for(int j=0;j<m;j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+num1);
                
                if(j+num2 < m) {
                    dp[i][j + num2] = Math.min(dp[i][j + num2], dp[i-1][j]);
                }
            }
        }
        
        int answer = INF;
        
        for(int i=0;i<m;i++) {
            answer = Math.min(dp[size][i], answer);
        }
        
        return answer >= n ? -1 : answer;
    }
}