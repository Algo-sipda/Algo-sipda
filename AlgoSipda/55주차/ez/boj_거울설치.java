import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj_거울설치 {

    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int startR, startC;

    static class Node implements Comparable<Node> {
        int r, c, dir, cnt;
        public Node(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N][4];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '#') {
                    startR = i;
                    startC = j;
                }
            }
        }
        map[startR][startC] = '.';
        bfs();
    }

    private static void bfs() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startR, startC, -1, 0));

        for (int i = 0; i < 4; i++) {
            visited[startR][startC][i] = true;
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (map[cur.r][cur.c] == '#') {
                System.out.println(cur.cnt);
                return;
            }

            if (cur.dir == -1) {
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (isOut(nr, nc)) continue;
                    if (map[nr][nc] != '*') {
                        queue.add(new Node(nr, nc, i, cur.cnt));
                        visited[nr][nc][i] = true;
                    }
                }
            } else {
                // 거울 세울수도
                if (map[cur.r][cur.c] == '!') {
                    int ds = 0;
                    int de = 0;
                    if (cur.dir == 0 || cur.dir == 1) {
                        ds = 2;
                        de = 4;
                    } else {
                        ds = 0;
                        de = 2;
                    }
                    for (int i = ds; i < de; i++) {
                        int nr = cur.r + dr[i];
                        int nc = cur.c + dc[i];
                        if (isOut(nr, nc) || visited[nr][nc][i]) continue;
                        if (map[nr][nc] != '*') {
                            queue.add(new Node(nr, nc, i, cur.cnt + 1));
                            visited[nr][nc][i] = true;
                        }
                    }
                }

                // 거울 안 세울수도
                int nr = cur.r + dr[cur.dir];
                int nc = cur.c + dc[cur.dir];
                if (isOut(nr, nc)) continue;
                if (map[nr][nc] != '*' && !visited[nr][nc][cur.dir]) {
                    queue.add(new Node(nr, nc, cur.dir, cur.cnt));
                    visited[nr][nc][cur.dir] = true;
                }
            }
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}
