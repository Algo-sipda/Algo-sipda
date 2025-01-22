import java.util.*;

class Solution {

    static int N, M, answer;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int endR, endC;

    public int solution(String[] board) {
        answer = -1;
        N = board.length;
        M = board[0].length();
        map = new char[N][M];
        int startR = 0;
        int startC = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'R') {
                    startR = i;
                    startC = j;
                } else if (map[i][j] == 'G') {
                    endR = i;
                    endC = j;
                }
            }
        }

        bfs(startR, startC);

        return answer;
    }

    private void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[N][M];
        queue.add(new int[] {r, c, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                int[] next = getNext(cur[0], cur[1], i);
                if (next[0] == endR && next[1] == endC) {
                    answer = cur[2] + 1;
                    return;
                }
                if (visited[next[0]][next[1]]) continue;

                queue.add(new int[] {next[0], next[1], cur[2] + 1});
                visited[next[0]][next[1]] = true;
            }
        }
    }

    private boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }

    private int[] getNext(int r, int c, int dir) {

        while (true) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (isOut(nr, nc) || map[nr][nc] == 'D') {
                return new int[] {r, c};
            }

            r = nr;
            c = nc;
        }
    }
}