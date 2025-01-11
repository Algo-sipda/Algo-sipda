class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;

        int[][] dp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        int max = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                        dp[i][j] = max;
                    }
                    answer = Math.max(answer, dp[i][j]);
                }

            }
        }

        return answer * answer;
    }
}
