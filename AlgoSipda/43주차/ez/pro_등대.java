import java.util.*;
class pro_등대 {

    List<Integer>[] adjList;
    int answer;

    public int solution(int n, int[][] lighthouse) {
        adjList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] light : lighthouse) {
            adjList[light[0]].add(light[1]);
            adjList[light[1]].add(light[0]);
        }

        dfs(0, 1);
        return answer;
    }

    private int dfs(int prev, int cur) {
        if (adjList[cur].size() == 1 && adjList[cur].get(0) == prev) {
            return 1;
        }
        int cnt = 0;
        for (int next : adjList[cur]) {
            if (next == prev) continue;
            cnt += dfs(cur, next);
        }
        if (cnt == 0) {
            return 1;
        }
        answer++;
        return 0;
    }
}