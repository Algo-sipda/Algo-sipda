
class pro_거스름돈 {
    public int solution(int n, int[] money) {
        int answer = 0;
        int[][] dp = new int[money.length + 1][n + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (j - money[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - money[i - 1]] % 1_000_000_007;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[money.length][n];
    }
}
/*
money = {1, 2, 5}
  0 1 2 3 4 5  -> n
0 0 0 0 0 0 0
1 1 1 1 1 1 1
2 1 1 2 2 3 3
5 1 1 2 2 3 4

dp[i][j] = dp[i - 1][j] + dp[i][j - money[i - 1]]
*/
