import java.util.*;

class Solution {
    static int MAX_VALUE = Integer.MAX_VALUE;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
            graph.get(i).add(i);
        }

        for (int[] edge : edge_list) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            Arrays.fill(dp[i], MAX_VALUE);
        }
        dp[1][gps_log[0]] = 0;

        // 시간마다 모든 노드를 탐색하면서 도달 가능한 경우 최소 수정 횟수 저장
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                // i 시간에 j 노드 도달 불가
                if (dp[i][j] == MAX_VALUE) {
                    continue;
                }
                // 다음 노드로 넘어갈 경우 수정 횟수 계산
                for (int next : graph.get(j)) {
                    int diff = gps_log[i] == next ? 0 : 1;
                    dp[i + 1][next] = Math.min(dp[i + 1][next], dp[i][j] + diff);
                }
            }
        }

        int answer = dp[k][gps_log[k - 1]];
        if (answer == MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }
}

// 시간마다 1~N 노드를 확인하면서
// 이웃 노드를 다음 노드로 해서 넘어갈 때 최소 수정 횟수를 Dp에 저장
// 현재 시간에서 dp 값이 inf 이면 도달 불가 (도달 가능했으면 이전 단계에서 갱신됐을 테니까)
