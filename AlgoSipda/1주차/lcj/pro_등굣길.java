class Solution {
    public int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] board = new int[n+1][m+1];
        
        for(int i=0;i<puddles.length;i++){
            board[puddles[i][1]][puddles[i][0]] = -1; 
        }
        board[1][1] = 1;
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(board[i][j] == -1) continue;
                if(board[i - 1][j] > 0) board[i][j] += board[i - 1][j] % MOD;
                if(board[i][j - 1] > 0) board[i][j] += board[i][j - 1] % MOD;
            }
        }
        return board[n][m] % MOD;
    }
}