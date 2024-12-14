class Solution {
    static int MOD = 20170805;
    static int[] dx = {0, -1};
    static int[] dy = {-1, 0};
    
    public int solution(int m, int n, int[][] cityMap) {
        if (m == 1 && n == 1) {
            return 1;
        }
        
        int[][][] dp = new int[m][n][2];
        
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        cityMap[0][0] = 2;
        
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (cityMap[x][y] == 1) {
                    continue;
                }
                for (int d = 0; d < 2; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (cityMap[nx][ny] == 2) {
                        dp[x][y][d] += dp[nx][ny][d] % MOD;
                    } else {
                        dp[x][y][d] += dp[nx][ny][0] % MOD + dp[nx][ny][1] % MOD;
                    }
                }
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}