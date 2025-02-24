import java.util.*;

class Solution {
    static final int N = 4;
    static boolean[] isNum, visitedPermutation;
    static int size = 0;
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(int[][] board, int r, int c) {
        answer = Integer.MAX_VALUE;
        isNum = new boolean[7];
        visitedPermutation = new boolean[7];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0 || isNum[board[i][j]])
                    continue;
                isNum[board[i][j]] = true;
                size++;
            }
        }

        permutation(0, new int[size], board, r, c);

        return answer;
    }

    public static void bfs(int[][] board, int[] arr, int sr, int sc) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        boolean[][] boardVisited = new boolean[N][N];
        int cnt = 0;
        int answerCnt = 0;
        int idx = 0;
        boolean isSecond = false;

        q.add(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int len = q.size();

            for (int l = 0; l < len; l++) {
                Point now = q.poll();

                if (board[now.x][now.y] == arr[idx] && !boardVisited[now.x][now.y]) {
                    answerCnt++;
                    answerCnt += cnt;
                    cnt = -1;
                    boardVisited[now.x][now.y] = true;
                    q.clear();
                    visited = new boolean[N][N];
                    q.add(new Point(now.x, now.y));
                    visited[now.x][now.y] = true;

                    if (!isSecond) {
                        isSecond = true;
                    } else {
                        isSecond = false;
                        idx++;
                        if (idx >= arr.length) {
                            answer = Math.min(answer, answerCnt);
                            return;
                        }
                    }
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (!checkRange(nx, ny) || visited[nx][ny])
                        continue;
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x;
                    int ny = now.y;

                    while (checkRange(nx + dx[i], ny + dy[i])) {
                        nx += dx[i];
                        ny += dy[i];
                        if (!boardVisited[nx][ny] && board[nx][ny] != 0)
                            break;
                    }

                    if (!checkRange(nx, ny) || visited[nx][ny])
                        continue;
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
            cnt++;
        }
    }

    public static boolean checkRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static void permutation(int cnt, int[] arr, int[][] board, int r, int c) {
        if (size == cnt) {
            bfs(board, arr, r, c);
            return;
        }

        for (int i = 1; i <= 6; i++) {
            if (!isNum[i] || visitedPermutation[i])
                continue;
            visitedPermutation[i] = true;
            arr[cnt] = i;
            permutation(cnt + 1, arr, board, r, c);
            visitedPermutation[i] = false;
        }
    }
}
