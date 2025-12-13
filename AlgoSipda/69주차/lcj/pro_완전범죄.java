import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int size = info.length;
        int[][] dp = new int[size + 1][m];
        int INF = 999999;
        
        for(int i=0;i<=size;i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        
        for(int i=1;i<=size;i++) {
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j=0;j<m;j++) {
                // a 선택
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                
                if(b + j < m) {
                    dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
                }
            }
        }
        
        int answer = INF;
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[i].length;j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        
        for(int i=0;i<m;i++) {
            answer = Math.min(answer, dp[size][i]);
        }
        
        return answer >=n?-1:answer;
    }
}