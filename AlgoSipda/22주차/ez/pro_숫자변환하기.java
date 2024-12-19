package ez;

import java.util.*;

class Solution {

    public int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }

    private static int bfs(int x, int y, int n) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[1000001];
        queue.add(new int[] { x, 0 });
        visited[x] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == y) {
                return cur[1];
            }

            if (cur[0] * 3 <= y && !visited[cur[0] * 3]) {
                queue.add(new int[] { cur[0] * 3, cur[1] + 1});
                visited[cur[0] * 3] = true;
            }

            if (cur[0] * 2 <= y && !visited[cur[0] * 2]) {
                queue.add(new int[] { cur[0] * 2, cur[1] + 1});
                visited[cur[0] * 2] = true;

            }

            if (cur[0] + n <= y && !visited[cur[0] + n]) {
                queue.add(new int[] { cur[0] + n, cur[1] + 1});
                visited[cur[0] + n] = true;
            }
        }

        return -1;
    }
}
