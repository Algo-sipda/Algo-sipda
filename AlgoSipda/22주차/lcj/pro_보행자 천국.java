import java.util.*;

/*
    우회전 금지
    m x n => city_map
    자동차는 오른쪽 또는 아래 방향으로 한 칸씩 이동 가능(2가지 방향)
    
    city_map 값
    0: 자동차가 자유롭게 지나다닐 수 있음
    1: 자동차 통행이 금지
    2: 보행자 안전 -> 좌회전 or 우회전 금지 => 왼쪽 -> 오른쪽, 위 -> 아래로만..
    
    출력: 이동 가능한 전체 경로의 수를 mod 값으로 나누어 리턴
*/

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        // int answer = 0;
        int[][][] dp = new int[m+1][n+1][2];// x거리, y거리, 방향
        
        dp[0][0][0] = 1;
        
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(cityMap[i][j] == 0) {
                    // 자동차 자유롭게 지나다닐 수 있음
                    // 오른쪽 or 아래로 이동 가능
                    dp[i+1][j][0] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                    dp[i][j+1][1] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                }
                else if(cityMap[i][j] == 2) {
                    // 보행자 안전
                    // 좌회전 or 우회전 금지 => 왼쪽 -> 오른쪽, 위 -> 아래로만..
                    dp[i+1][j][0] += dp[i][j][0] % MOD;
                    dp[i][j+1][1] += dp[i][j][1] % MOD;
                }
            }
        }
        return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;
    }
}