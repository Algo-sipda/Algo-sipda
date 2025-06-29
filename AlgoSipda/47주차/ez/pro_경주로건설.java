import java.util.*;

class pro_경주로건설 {

    int N, answer;
    int[][] visited;

    public int solution(int[][] board) {
        N = board.length;
        answer = Integer.MAX_VALUE;
        visited = new int[N][N];
        bfs(board);
        return answer;
    }

    private void bfs(int[][] board) {
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0, 0, 100});
        queue.add(new int[] {0, 0, 1, 100});
        visited[0][0] = 100;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N - 1 && cur[1] == N - 1) {
                answer = Math.min(answer, cur[3] - 100);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || board[nr][nc] == 1) continue;
                int cost = (cur[2] == i) ? 100 : 600;
                if (visited[nr][nc] == 0) {
                    visited[nr][nc] = cur[3] + cost;
                    queue.add(new int[] {nr, nc, i, visited[nr][nc]});
                } else if (cur[3] + cost < visited[nr][nc] + 500) {
                    visited[nr][nc] = cur[3] + cost;
                    queue.add(new int[] {nr, nc, i, cur[3] + cost});
                }
            }
        }
    }

    private boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}
