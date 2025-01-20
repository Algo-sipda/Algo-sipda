import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class boj_puyopuyo {

    static final int N = 12;
    static final int M = 6;
    static int cnt;
    static char[][] map = new char[N][M];
    static boolean[][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        while (true) {
            if (!start()) {
                System.out.println(cnt);
                return;
            }

            push();
            cnt++;
        }
    }

    private static void push() {
        for (int i = 0; i < M; i++) {
            Queue<Character> queue = new ArrayDeque<>();
            for (int j = N - 1; j >= 0; j--) {
                if (map[j][i] != '.') {
                    queue.add(map[j][i]);
                }
            }

            int idx = N - 1;
            while (!queue.isEmpty()) {
                map[idx][i] = queue.poll();
                idx -= 1;
            }
            for (int j = idx; j >= 0; j--) {
                map[j][i] = '.';
            }
        }
    }

    private static boolean start() {
        boolean isBomb = false;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0;j < M; j++) {
                if (map[i][j] != '.' && !visited[i][j]) {
                    if (bfs(i, j, map[i][j])) {
                        isBomb = true;
                    }
                }
            }
        }

        if (isBomb) {
            return true;
        }
        return false;
    }

    private static boolean bfs(int r, int c, char color) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        char[][] copy = copyMap(map);
        copy[r][c] = '.';
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || visited[nr][nc] || copy[nr][nc] != color) continue;
                visited[nr][nc] = true;
                queue.add(new int[] { nr, nc });
                copy[nr][nc] = '.';
                cnt++;
            }
        }

        if (cnt >= 4) {
            map = copyMap(copy);
            return true;
        }
        return false;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }

    private static char[][] copyMap(char[][] map) {
        char[][] copy = new char[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }
}
