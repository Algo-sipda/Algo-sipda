class Solution {
    static boolean[][][] visited;
    static int N, M, answer;
    static int[][] items;

    public int solution(int[][] info, int n, int m) {
        visited = new boolean[info.length][n][m];
        answer = n;
        N = n;
        M = m;
        items = info;

        dfs(0, 0, 0);

        if (answer == n)
            return -1;

        return answer;
    }

    static void dfs(int i, int a, int b) {
        if (i == items.length) {
            answer = Math.min(answer, a);
            return;
        }

        if (visited[i][a][b])
            return;

        visited[i][a][b] = true;

        if (a + items[i][0] >= N && b + items[i][1] >= M)
            return;

        if (a + items[i][0] < N)
            dfs(i + 1, a + items[i][0], b);
        if (b + items[i][1] < M)
            dfs(i + 1, a, b + items[i][1]);
    }
}
