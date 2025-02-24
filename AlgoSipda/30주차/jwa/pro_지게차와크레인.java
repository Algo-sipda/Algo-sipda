import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        char[][] area = new char[n][m];
        int count = n * m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                area[i][j] = storage[i].charAt(j);
            }
        }

        for (String request : requests) {
            int len = request.length();
            char container = request.charAt(0);
            List<Node> containers = new ArrayList<>(); // 꺼낼 컨테이너 위치

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (area[i][j] == container) {
                        if (len == 2) {
                            count--;
                            area[i][j] = '0';
                        } else {
                            boolean flag = false;
                            boolean[][] visited = new boolean[n][m];
                            ArrayDeque<Node> q = new ArrayDeque<>();
                            q.offer(new Node(i, j));
                            visited[i][j] = true;

                            Top: while (!q.isEmpty()) {
                                Node curr = q.poll();

                                for (int d = 0; d < 4; d++) {
                                    int nx = curr.x + dx[d];
                                    int ny = curr.y + dy[d];

                                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                                        flag = true;
                                        break Top;
                                    }

                                    if (visited[nx][ny])
                                        continue;

                                    if (area[nx][ny] == '0') {
                                        q.offer(new Node(nx, ny));
                                        visited[nx][ny] = true;
                                    }
                                }
                            }

                            if (flag) {
                                count--;
                                containers.add(new Node(i, j));
                            }
                        }
                    }
                }
            }

            for (Node con : containers) {
                area[con.x][con.y] = '0';
            }
        }

        return count;
    }
}
