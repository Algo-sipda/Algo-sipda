import java.util.*;

class Solution {
    static int n;
    static boolean[][] visited;
    static int[][] group;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] land, int height) {
        n = land.length;
        visited = new boolean[n][n];
        group = new int[n][n];
        int groupNum = 1;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (!visited[x][y]) bfs(x, y, land, height, groupNum++);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    int g1 = group[x][y];
                    int g2 = group[nx][ny];
                    if (g1 != g2) {
                        int diff = Math.abs(land[x][y] - land[nx][ny]);
                        pq.offer(new int[]{g1, g2, diff});
                    }
                }
            }
        }

        int[] parent = new int[groupNum];
        for (int i = 1; i < groupNum; i++) parent[i] = i;

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int a = edge[0], b = edge[1], cost = edge[2];
            if (find(parent, a) != find(parent, b)) {
                union(parent, a, b);
                answer += cost;
            }
        }

        return answer;
    }

    private void bfs(int x, int y, int[][] land, int height, int groupNum) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        group[x][y] = groupNum;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (Math.abs(land[now[0]][now[1]] - land[nx][ny]) <= height) {
                    visited[nx][ny] = true;
                    group[nx][ny] = groupNum;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    private void union(int[] parent, int a, int b) {
        int pa = find(parent, a);
        int pb = find(parent, b);
        if (pa != pb) parent[pb] = pa;
    }
}
