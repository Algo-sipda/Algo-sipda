import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int N, M, H, W;
    static int[][] map;
    static int sr, sc, fr, fc;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        fr = Integer.parseInt(st.nextToken()) - 1;
        fc = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new Node(sc, sr, 0));
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.y == fr && cur.x == fc)
                return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx + W - 1 >= M || ny + H - 1 >= N) continue;
                if (visited[ny][nx]) continue;

                boolean canMove = true;
                if (i == 0) {
                    for (int row = ny; row < ny + H; row++) {
                        if (map[row][nx] == 1) {
                            canMove = false;
                            break;
                        }
                    }
                } else if (i == 1) {
                    for (int row = ny; row < ny + H; row++) {
                        if (map[row][nx + W - 1] == 1) {
                            canMove = false;
                            break;
                        }
                    }
                } else if (i == 2) {
                    for (int col = nx; col < nx + W; col++) {
                        if (map[ny][col] == 1) {
                            canMove = false;
                            break;
                        }
                    }
                } else if (i == 3) {
                    for (int col = nx; col < nx + W; col++) {
                        if (map[ny + H - 1][col] == 1) {
                            canMove = false;
                            break;
                        }
                    }
                }

                if (!canMove) continue;

                visited[ny][nx] = true;
                queue.offer(new Node(nx, ny, cur.cnt + 1));
            }
        }

        return -1;
    }
}