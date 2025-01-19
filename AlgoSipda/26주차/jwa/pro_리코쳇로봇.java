import java.util.*;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m;
    static char[][] area;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(String[] board) {
        Node start = new Node(-1, -1);
        Node goal = new Node(-1, -1);

        n = board.length;
        m = board[0].length();
        area = new char[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            int n = board[i].length();
            for (int j = 0; j < n; j++) {
                area[i][j] = board[i].charAt(j);

                if (area[i][j] == 'R') {
                    start = new Node(i, j);
                } else if (area[i][j] == 'G') {
                    goal = new Node(i, j);
                }
            }
        }

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        int answer = 0;

        while (!q.isEmpty()) {
            int count = q.size();

            for (int i = 0; i < count; i++) {
                Node curr = q.poll();

                if (curr.x == goal.x && curr.y == goal.y) {
                    return answer;
                }

                for (int d = 0; d < dx.length; d++) {
                    int nx = curr.x;
                    int ny = curr.y;

                    while (checkRange(nx + dx[d], ny + dy[d])) {
                        nx += dx[d];
                        ny += dy[d];
                    }

                    if (visited[nx][ny])
                        continue;

                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            answer++;
        }

        return -1;
    }

    public boolean checkRange(int nx, int ny) {
        if (nx < 0 || nx >= n || ny < 0 || ny >= m || area[nx][ny] == 'D') {
            return false;
        }
        return true;
    }
}
