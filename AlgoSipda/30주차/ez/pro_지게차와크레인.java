import java.util.*;

class pro_지게차와크레인 {

    char[][] map;
    boolean[][] visited;
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};

    public int solution(String[] storage, String[] requests) {
        int answer = storage.length * storage[0].length();
        map = new char[storage.length][storage[0].length()];
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[i].length(); j++) {
                map[i][j] = storage[i].charAt(j);
            }
        }

        for (String command : requests) {
            if (command.length() > 1) {
                // System.out.println("크레인 -> " + moveCrane(command.charAt(0)));
                answer -= moveCrane(command.charAt(0));
            } else {
                // System.out.println("포크레인 -> " + moveFork(command.charAt(0)));
                answer -= moveFork(command.charAt(0));
            }
        }
        return answer;
    }

    private int moveCrane(char alpha) {
        int cnt = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == alpha) {
                    map[i][j] = ' ';
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private int moveFork(char alpha) {
        int cnt = 0;
        char[][] newMap = copyMap();
        visited = new boolean[map.length][map[0].length];
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[i].length; j++) {
                if (i == 0 || i == newMap.length - 1 || j == 0 || j == newMap[i].length - 1) {
                    if (visited[i][j]) continue;
                    if (newMap[i][j] == alpha) {
                        cnt++;
                        map[i][j] = ' ';
                        visited[i][j] = true;
                    } else if (newMap[i][j] == ' ') {
                        cnt += bfs(i, j, alpha, newMap);
                    }
                }
            }
        }
        return cnt;
    }

    private int bfs(int r, int c, char alpha, char[][] newMap) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[r][c] = true;
        queue.add(new int[] { r, c });
        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (isOut(nr, nc) || visited[nr][nc]) continue;

                if (newMap[nr][nc] == ' ') {
                    queue.add(new int[] { nr, nc });
                    visited[nr][nc] = true;
                } else if (newMap[nr][nc] == alpha) {
                    map[nr][nc] = ' ';
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length;
    }

    private char[][] copyMap() {
        char[][] newMap = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}