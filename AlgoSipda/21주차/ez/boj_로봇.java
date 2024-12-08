import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class boj_로봇 {

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static State start, end;
    static int[] dr = {0, 0, 0, 1, -1};
    static int[] dc = {0, 1, -1, 0, 0};

    static class State {
        int r, c, dir, cnt;
        public State(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N][5];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        start = new State(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), 0);
        visited[start.r][start.c][start.dir] = true;
        st = new StringTokenizer(br.readLine());
        end = new State(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), 0);

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<State> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (cur.r == end.r && cur.c == end.c && cur.dir == end.dir) {
                return cur.cnt;
            }
            // 이동하거나
            for (int k = 1; k < 4; k++) {
                int nr = cur.r + (dr[cur.dir] * k);
                int nc = cur.c + (dc[cur.dir] * k);

                if (isOut(nr, nc) || visited[nr][nc][cur.dir]) continue;
                if (map[nr][nc] == 1) {
                    break;
                }

                visited[nr][nc][cur.dir] = true;
                queue.add(new State(nr, nc, cur.dir, cur.cnt + 1));
            }
            // 방향 바꾸거나
            int right = turnRight(cur.dir);
            if (!visited[cur.r][cur.c][right]) {
                visited[cur.r][cur.c][right] = true;
                queue.add(new State(cur.r, cur.c, right, cur.cnt + 1));
            }
            int left = turnLeft(cur.dir);
            if (!visited[cur.r][cur.c][left]) {
                visited[cur.r][cur.c][left] = true;
                queue.add(new State(cur.r, cur.c, left, cur.cnt + 1));
            }
        }
        return 0;
    }

    private static int turnRight(int dir) {
        int[] right = new int[] {1, 3, 2, 4};
        for (int i = 0; i < 4; i++) {
            if (right[i] == dir) {
                return right[(i + 1) % 4];
            }
        }
        return 0;
    }

    private static int turnLeft(int dir) {
        int[] left = new int[] {1, 4, 2, 3};
        for (int i = 0; i < 4; i++) {
            if (left[i] == dir) {
                return left[(i + 1) % 4];
            }
        }
        return 0;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= M || nc >= N;
    }
}