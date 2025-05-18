import java.util.*;

class pro_무인도여행 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> area;

    public int[] solution(String[] maps) {
        int[] answer;
        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        visited = new boolean[N][M];
        area = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = (maps[i].charAt(j) == 'X') ? 0 : maps[i].charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                visited[i][j] = true;
                bfs(i, j);
            }
        }

        if (area.size() == 0) {
            return new int[] {-1};
        }
        answer = new int[area.size()];
        for (int i = 0; i < area.size(); i++) {
            answer[i] = area.get(i);
        }
        Arrays.sort(answer);

        return answer;
    }

    private void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int max = map[r][c];
        queue.add(new int[] {r, c});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
                max += map[nr][nc];
            }
        }
        area.add(max);
    }

    private boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }
}