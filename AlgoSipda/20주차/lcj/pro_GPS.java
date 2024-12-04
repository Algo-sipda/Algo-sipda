import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;
        
        List<Integer>[] list = new List[n+1];
        int[][] dp = new int[k][n+1];
        
        for(int i=1;i<=n;i++) {
            list[i] = new ArrayList<>();
            list[i].add(i);
        }
        
        for(int i=0;i<m;i++) {
            int e1 = edge_list[i][0];
            int e2 = edge_list[i][1];
            list[e1].add(e2);
            list[e2].add(e1);
        }
        
        for(int i=0;i<k;i++) {
            for(int j=1; j<=n;j++) {
                dp[i][j] = 1001;
            }
        }
        
        dp[0][gps_log[0]] = 0;
        
        for(int i=0;i<k-1;i++) {
            for(int j=1;j<=n;j++) {
                if(dp[i][j] == 1001) continue;
                
                for(int a = 0;a<list[j].size();a++) {
                    int next = list[j].get(a);
                    int nv = 0;
                    
                    if(gps_log[i+1] != next ) nv = 1;
                    dp[i+1][next] = Math.min(dp[i+1][next], dp[i][j] + nv);
                }
            }
        }
        answer = dp[k-1][gps_log[k-1]];
        return answer == 1001?-1:answer;
    }
}