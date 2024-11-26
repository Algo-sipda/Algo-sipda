class pro_순위 {
    public int solution(int n, int[][] results) {
        int answer = 0;

        int[][] rank = new int[n + 1][n + 1];

        for (int[] r : results) {
            rank[r[0]][r[1]] = 1;
            rank[r[1]][r[0]] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (rank[i][k] == 1 && rank[k][j] == 1) {
                        rank[i][j] = 1;
                        rank[j][i] = -1;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (rank[i][j] != 0) {
                    cnt++;
                }
            }
            if (cnt == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}