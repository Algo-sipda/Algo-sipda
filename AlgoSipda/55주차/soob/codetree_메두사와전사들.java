import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
    static int nextInt() throws IOException { return Integer.parseInt(next()); }

    static final int[][] P1 = { {-1,0}, {1,0}, {0,-1}, {0,1} };
    static final int[][] P2 = { {0,-1}, {0,1}, {-1,0}, {1,0} };
    static final int[][][] VISION = {
            { {-1,-1}, {-1,0}, {-1,1} },
            { { 1,-1}, { 1,0}, { 1,1} },
            { {-1,-1}, { 0,-1}, { 1,-1} },
            { {-1, 1}, { 0, 1}, { 1, 1} }
    };

    static boolean inRange(int x, int y, int N) { return 0 <= x && x < N && 0 <= y && y < N; }
    static int manhattan(int ax, int ay, int bx, int by) { return Math.abs(ax - bx) + Math.abs(ay - by); }

    static class WarriorMap {
        final int N;
        final ArrayList<int[]> warriors;
        final ArrayList<HashSet<Integer>> cells;

        WarriorMap(int N, List<int[]> init) {
            this.N = N;
            this.warriors = new ArrayList<>(init);
            this.cells = new ArrayList<>(N * N);
            for (int i = 0; i < N * N; i++) cells.add(new HashSet<>());
            for (int i = 0; i < warriors.size(); i++) {
                int[] w = warriors.get(i);
                cells.get(idx(w[0], w[1])).add(i);
            }
        }

        int idx(int x, int y) { return x * N + y; }

        void removeWarrior(int wi) {
            int last = warriors.size() - 1;
            int[] cur = warriors.get(wi);
            cells.get(idx(cur[0], cur[1])).remove(wi);
            if (wi != last) {
                int[] tail = warriors.get(last);
                warriors.set(wi, tail);
                HashSet<Integer> cell = cells.get(idx(tail[0], tail[1]));
                cell.remove(last);
                cell.add(wi);
            }
            warriors.remove(last);
        }

        int removeSameCell(int mx, int my) {
            int removed = 0;
            int i = 0;
            while (i < warriors.size()) {
                int[] w = warriors.get(i);
                if (w[0] == mx && w[1] == my) { removeWarrior(i); removed++; }
                else i++;
            }
            return removed;
        }

        boolean isWarriorAt(int x, int y) { return !cells.get(idx(x, y)).isEmpty(); }

        int moveOnce(int wi, int mx, int my, int[][] vision, int[][] pri) {
            int[] w = warriors.get(wi);
            int x = w[0], y = w[1];
            int d0 = manhattan(x, y, mx, my);
            for (int[] d : pri) {
                int nx = x + d[0], ny = y + d[1];
                if (!inRange(nx, ny, N)) continue;
                if (vision[nx][ny] == 1) continue;
                if (manhattan(nx, ny, mx, my) < d0) {
                    cells.get(idx(x, y)).remove(wi);
                    w[0] = nx; w[1] = ny;
                    cells.get(idx(nx, ny)).add(wi);
                    return 1;
                }
            }
            return 0;
        }

        int[] moveAll(int[][] vision, int mx, int my) {
            removeSameCell(mx, my);
            int steps = 0;
            for (int i = 0; i < warriors.size(); i++) {
                int[] w = warriors.get(i);
                if (vision[w[0]][w[1]] == 0) {
                    steps += moveOnce(i, mx, my, vision, P1);
                    steps += moveOnce(i, mx, my, vision, P2);
                }
            }
            int attackers = removeSameCell(mx, my);
            return new int[]{steps, attackers};
        }
    }

    static class VisionResult {
        int[][] map;
        int seen;
        VisionResult(int[][] map, int seen) { this.map = map; this.seen = seen; }
    }

    static VisionResult buildVision(int N, WarriorMap wm, int mx, int my, int[][] tri) {
        int[][] vis = new int[N][N];
        int seen = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> occ = new ArrayDeque<>();
        q.add(new int[]{mx, my});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int i = 0; i < 3; i++) {
                int nx = x + tri[i][0], ny = y + tri[i][1];
                if (!inRange(nx, ny, N) || vis[nx][ny] == 1) continue;
                if (wm.isWarriorAt(nx, ny)) {
                    int t;
                    if (nx == mx || ny == my) t = 1;
                    else {
                        boolean same = (nx - mx) * tri[0][0] > 0 && (ny - my) * tri[0][1] > 0;
                        t = same ? 0 : 2;
                    }
                    occ.add(new int[]{nx, ny, t});
                }
                vis[nx][ny] = 1;
                q.add(new int[]{nx, ny});
            }
        }
        while (!occ.isEmpty()) {
            int[] cur = occ.poll();
            int x = cur[0], y = cur[1], t = cur[2];
            for (int d = 0; d < 3; d++) {
                if (t == 1 && d != 1) continue;
                if (t == 0 && d == 2) continue;
                if (t == 2 && d == 0) continue;
                int nx = x + tri[d][0], ny = y + tri[d][1];
                if (!inRange(nx, ny, N) || vis[nx][ny] == 0) continue;
                vis[nx][ny] = 0;
                occ.add(new int[]{nx, ny, t});
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (vis[i][j] == 1) seen += wm.cells.get(i * N + j).size();
        return new VisionResult(vis, seen);
    }

    static int[][] bfsDistFromTarget(int[][] road, int ex, int ey) {
        int N = road.length;
        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, -1);
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{ex, ey});
        dist[ex][ey] = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1];
            for (int[] d : P1) {
                int nx = x + d[0], ny = y + d[1];
                if (!inRange(nx, ny, N) || road[nx][ny] == 1 || dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[x][y] + 1;
                dq.add(new int[]{nx, ny});
            }
        }
        return dist;
    }

    static int[] moveMedusaOne(int[][] dist, int x, int y) {
        int N = dist.length;
        for (int[] d : P1) {
            int nx = x + d[0], ny = y + d[1];
            if (inRange(nx, ny, N) && dist[nx][ny] != -1 && dist[nx][ny] < dist[x][y])
                return new int[]{nx, ny};
        }
        return new int[]{x, y};
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int N = nextInt();
        int M = nextInt();
        int sx = nextInt(), sy = nextInt(), ex = nextInt(), ey = nextInt();
        ArrayList<int[]> init = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            int ax = nextInt(), ay = nextInt();
            init.add(new int[]{ax, ay});
        }
        int[][] road = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                road[i][j] = nextInt();

        int[][] dist = bfsDistFromTarget(road, ex, ey);
        if (dist[sx][sy] == -1) {
            System.out.println(-1);
            return;
        }

        WarriorMap wm = new WarriorMap(N, init);
        int mx = sx, my = sy;

        while (!(mx == ex && my == ey)) {
            int[] nxt = moveMedusaOne(dist, mx, my);
            mx = nxt[0]; my = nxt[1];
            if (mx == ex && my == ey) {
                System.out.println(0);
                break;
            }
            int bestSeen = -1;
            int[][] bestVision = null;
            for (int d = 0; d < 4; d++) {
                VisionResult vr = buildVision(N, wm, mx, my, VISION[d]);
                if (vr.seen > bestSeen) {
                    bestSeen = vr.seen;
                    bestVision = vr.map;
                }
            }
            int[] res = wm.moveAll(bestVision, mx, my);
            int steps = res[0], attackers = res[1];
            System.out.println(steps + " " + bestSeen + " " + attackers);
        }
    }
}