class Solution {

    int MOD = 20170805;
    static int N, M;
    static int[] dx = {0, -1};
    static int[] dy = {-1, 0};

    public int solution(int m, int n, int[][] cityMap) {
        N = m;
        M = n;

        int [][][] dp = new int [N][M][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        cityMap[0][0] = 2;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(cityMap[i][j] == 1) continue;

                for(int k = 0; k < 2; k++){
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];
                    if(!valid(nextX, nextY) || cityMap[nextX][nextY] == 1) continue;
                    if(cityMap[nextX][nextY] == 2) {
                        dp[i][j][k] += dp[nextX][nextY][k];
                    }
                    else{
                        dp[i][j][k] += dp[nextX][nextY][0] + dp[nextX][nextY][1];
                    }
                    dp[i][j][k] %= MOD;
                }
            }
        }
        return (dp[N - 1][M - 1][0] + dp[N - 1][M - 1][1]) % MOD;
    }

    public static boolean valid(int nextX, int nextY){
        if(0 <= nextX && 0 <= nextY && nextX < N && nextY < M) return true;

        return false;
    }
}
