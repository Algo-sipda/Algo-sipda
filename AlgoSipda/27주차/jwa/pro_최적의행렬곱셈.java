import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int start = 0; start + i < n; start++) {
                int end = start + i;

                for (int j = start; j < end; j++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][j] + dp[j + 1][end]
                            + (matrix_sizes[start][0] * matrix_sizes[j][1] * matrix_sizes[end][1]));
                }
            }
        }

        answer = dp[0][n - 1];

        return answer;
    }
}
