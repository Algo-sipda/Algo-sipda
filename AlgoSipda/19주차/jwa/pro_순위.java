import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int INF = 1000000;
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for (int[] result : results) {
            int A = result[0];
            int B = result[1];
            dist[A][B] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            boolean knowRank = true;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (dist[i][j] == INF && dist[j][i] == INF) {
                    knowRank = false;
                    break;
                }
            }
            if (knowRank) answer++;
        }

        return answer;
    }
}
