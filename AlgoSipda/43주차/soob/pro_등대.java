import java.util.*;

class Solution {
    List<List<Integer>> graph;
    int[][] dp;
    boolean[] visited;

    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int[] edge : lighthouse) {
            int a = edge[0], b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int next : graph.get(node)) {
            if (visited[next]) continue;
            dfs(next);

            dp[node][0] += dp[next][1];
            dp[node][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}