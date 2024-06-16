import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {

        int[][] map = new int[n][m];
        for (int[] puddle : puddles) {
            map[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            if (flag)
                map[0][i] = 0;
            else {
                if (map[0][i] == -1) {
                    flag = true;
                    continue;
                }
                map[0][i] = 1;
            }
        }

        flag = false;
        for (int i = 0; i < n; i++) {
            if (flag)
                map[i][0] = 0;
            else {
                if (map[i][0] == -1) {
                    flag = true;
                    continue;
                }
                map[i][0] = 1;
            }
        }

        for (int y = 1; y < n; y++) {
            for (int x = 1; x < m; x++) {
                if (map[y][x] == 0) {
                    if (map[y - 1][x] != -1)
                        map[y][x] += map[y - 1][x];
                    if (map[y][x - 1] != -1)
                        map[y][x] += map[y][x - 1];
                    map[y][x] %= 1000000007;
                }
            }
        }

        return map[n - 1][m - 1];
    }
}