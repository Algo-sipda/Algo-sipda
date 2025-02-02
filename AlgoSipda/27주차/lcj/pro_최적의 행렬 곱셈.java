class Solution {
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        int[][] dp = new int[matrix_sizes.length][matrix_sizes.length];
        
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp.length;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp.length-i;j++) {
                if(j == (j+i)) dp[j][j+i] = 0;
                else {
                    for(int k=j;k<i+j;k++) {
                        dp[j][j+i] = Math.min(dp[j][j+i], dp[j][k] + dp[k+1][j+i] + matrix_sizes[j][0] * matrix_sizes[k][1] * matrix_sizes[j+i][1]);
                    }
                }
                
            }
        }
        return dp[0][matrix_sizes.length-1];
    }
}