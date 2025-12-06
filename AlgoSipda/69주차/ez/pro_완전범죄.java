class pro_완전범죄 {
    public int solution(int[][] info, int n, int m) {
        int[][] memo = new int[info.length][m];
        int INF = 120;
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = INF;
            }
        }
        memo[0][0] = info[0][0];
        if (info[0][1] < m) {
            memo[0][info[0][1]] = 0;
        }

        if (info.length == 1) {
            return info[0][1] < m ? 0 : info[0][0];
        }

        for (int i = 1; i < info.length; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = Math.min(memo[i][j], memo[i - 1][j] + info[i][0]);
                if (j + info[i][1] < m) {
                    memo[i][j + info[i][1]] = Math.min(memo[i][j + info[i][1]], memo[i - 1][j]);
                }
            }
        }

        int answer = INF;
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, memo[info.length - 1][j]);
        }

        return (answer < n) ? answer : -1;
    }
}