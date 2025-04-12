import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static List<Player> players = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] exit = new int[2];
    static int totalMove = 0;

    static class Player {
        int x, y;
        boolean finished;

        Player(int x, int y) {
            this.x = x;
            this.y = y;
            this.finished = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            players.add(new Player(x, y));
        }

        st = new StringTokenizer(br.readLine());
        exit[0] = Integer.parseInt(st.nextToken()) - 1;
        exit[1] = Integer.parseInt(st.nextToken()) - 1;

        for (int t = 0; t < K; t++) {
            moveAll();
            if (isFinished()) break;
            rotateMaze();
        }

        System.out.println(totalMove);
        System.out.println((exit[0] + 1) + " " + (exit[1] + 1));
    }

    static void moveAll() {
        for (Player p : players) {
            if (p.finished) continue;
            int minDist = dist(p.x, p.y);
            int nx = p.x, ny = p.y;

            for (int d = 0; d < 4; d++) {
                int tx = p.x + dx[d];
                int ty = p.y + dy[d];
                if (!inRange(tx, ty) || map[tx][ty] > 0) continue;
                int curDist = dist(tx, ty);
                if (curDist < minDist) {
                    minDist = curDist;
                    nx = tx;
                    ny = ty;
                }
            }

            if (nx != p.x || ny != p.y) {
                p.x = nx;
                p.y = ny;
                totalMove++;
            }

            if (p.x == exit[0] && p.y == exit[1]) {
                p.finished = true;
            }
        }
    }

    static boolean isFinished() {
        for (Player p : players) {
            if (!p.finished) return false;
        }
        return true;
    }

    static int dist(int x, int y) {
        return Math.abs(x - exit[0]) + Math.abs(y - exit[1]);
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static void rotateMaze() {
        int size = 1;
        int sx = -1, sy = -1;

        outer:
        for (; size <= N; size++) {
            for (int i = 0; i <= N - size; i++) {
                for (int j = 0; j <= N - size; j++) {
                    if (containsExit(i, j, size) && containsPlayer(i, j, size)) {
                        sx = i;
                        sy = j;
                        break outer;
                    }
                }
            }
        }

        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                tmp[j][size - i - 1] = Math.max(0, map[sx + i][sy + j] - 1);

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                map[sx + i][sy + j] = tmp[i][j];

        for (Player p : players) {
            if (p.finished) continue;
            if (sx <= p.x && p.x < sx + size && sy <= p.y && p.y < sy + size) {
                int tx = p.y - sy;
                int ty = size - 1 - (p.x - sx);
                p.x = sx + tx;
                p.y = sy + ty;
            }
        }

        if (sx <= exit[0] && exit[0] < sx + size && sy <= exit[1] && exit[1] < sy + size) {
            int tx = exit[1] - sy;
            int ty = size - 1 - (exit[0] - sx);
            exit[0] = sx + tx;
            exit[1] = sy + ty;
        }
    }

    static boolean containsExit(int sx, int sy, int size) {
        return (sx <= exit[0] && exit[0] < sx + size && sy <= exit[1] && exit[1] < sy + size);
    }

    static boolean containsPlayer(int sx, int sy, int size) {
        for (Player p : players) {
            if (!p.finished && sx <= p.x && p.x < sx + size && sy <= p.y && p.y < sy + size)
                return true;
        }
        return false;
    }
}

