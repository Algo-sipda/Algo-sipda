import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int N;
    static char[][] g;
    static int sx, sy, tx, ty;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        g = new char[N][N];
        boolean found = false;
        for (int y = 0; y < N; y++) {
            String s = br.readLine();
            for (int x = 0; x < N; x++) {
                g[y][x] = s.charAt(x);
                if (g[y][x] == '#') {
                    if (!found) { sx = x; sy = y; found = true; }
                    else { tx = x; ty = y; }
                }
            }
        }
        System.out.println(solve());
    }

    static int solve() {
        int[][][] dist = new int[4][N][N];
        for (int d = 0; d < 4; d++) for (int y = 0; y < N; y++) Arrays.fill(dist[d][y], INF);
        Deque<int[]> dq = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            dist[d][sy][sx] = 0;
            dq.add(new int[]{sx, sy, d});
        }
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int x = cur[0], y = cur[1], d = cur[2];
            int cost = dist[d][y][x];
            int nx = x + dx[d], ny = y + dy[d];
            if (!in(nx, ny) || g[ny][nx] == '*') continue;
            if (dist[d][ny][nx] > cost) {
                dist[d][ny][nx] = cost;
                dq.addFirst(new int[]{nx, ny, d});
            }
            if (g[ny][nx] == '!' || g[ny][nx] == '#') {
                int ld = (d + 3) % 4, rd = (d + 1) % 4;
                if (dist[ld][ny][nx] > cost + 1) {
                    dist[ld][ny][nx] = cost + 1;
                    dq.addLast(new int[]{nx, ny, ld});
                }
                if (dist[rd][ny][nx] > cost + 1) {
                    dist[rd][ny][nx] = cost + 1;
                    dq.addLast(new int[]{nx, ny, rd});
                }
            }
        }
        int ans = INF;
        for (int d = 0; d < 4; d++) if (ans > dist[d][ty][tx]) ans = dist[d][ty][tx];
        return ans;
    }

    static boolean in(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}