
/*
    풀이 방식: DP
    1. 웅덩이일 때(-1)
        map[i][j] = 0으로
    2. 행(i)이 0이 아니면
        map[i][j] += map[i-1][j];
    3. 열(j)이 0이 아니면
        map[i][j] += map[i][j-1];
 */

class Solution {

    static final int MOD = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] map = new int[n][m];
        for (int[] puddle : puddles) {
            map[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        map[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }

                if (i != 0) {
                    map[i][j] += map[i - 1][j] % MOD;
                }

                if (j != 0) {
                    map[i][j] += map[i][j - 1] % MOD;
                }
            }
        }

        return map[n-1][m-1] % MOD;
    }
}