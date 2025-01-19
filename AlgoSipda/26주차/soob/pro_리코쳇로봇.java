import java.util.*;
import java.awt.Point;

class Solution {
    int xLen, yLen, startX, startY, goalX, goalY;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] map;
    boolean[][] visited;

    public int solution(String[] board) {
        xLen = board[0].length();
        yLen = board.length;

        map = new int[yLen + 2][xLen + 2];
        Arrays.fill(map[0], 1);
        Arrays.fill(map[yLen + 1], 1);
        for (int i = 0; i < yLen + 2; i++) {
            map[i][0] = 1;
            map[i][xLen+1] = 1;
        }

        for (int y = 1; y <= yLen; y++) {
            for (int x = 1; x <= xLen; x++) {
                String b = board[y-1];
                if (b.charAt(x-1) == 'D')
                    map[y][x] = 1;
                if (b.charAt(x-1) == 'R') {
                    map[y][x] = 2;
                    startX = x;
                    startY = y;
                }
                if (b.charAt(x-1) == 'G') {
                    map[y][x] = 3;
                    goalX = x;
                    goalY = y;
                }
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        visited = new boolean[yLen+2][xLen+2];
        visited[startY][startX] = true;
        int answer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                if (p.x == goalX && p.y == goalY) {
                    return answer;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (!canGo(nx, ny)) continue;
                    if(map[ny][nx] == 1) continue;
                    Point np = slide(nx, ny, d);
                    if(!canGo(np.x, np.y)) continue;
                    if(map[np.y][np.x] == 1) continue;
                    if (!visited[np.y][np.x]) {
                        queue.add(np);
                        visited[np.y][np.x] = true;
                    }
                }
            }
            answer++;
        }

        return -1;
    }

    Point slide(int x, int y, int d) {
        while (canGo(x + dx[d], y + dy[d])) {
            if(map[y + dy[d]][x + dx[d]] == 1) break;
            x += dx[d];
            y += dy[d];
        }
        return new Point(x, y);
    }

    boolean canGo(int x, int y) {
        return 0 <= x && x <= xLen && 0 <= y && y <= yLen;
    }
}