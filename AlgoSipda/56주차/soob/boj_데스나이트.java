import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dx = {-1, 1, -2, 2, -1, 1};
    static int[] dy = {-2, -2, 0, 0, 2, 2};

    static int bfs(int sx, int sy, int tx, int ty) {
        int[][] dist = new int[n][n];
        for (int y = 0; y < n; y++) {
            Arrays.fill(dist[y], -1);
        }
        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[sy][sx] = 0;
        q.add(new int[]{sx, sy});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            if (x == tx && y == ty) return dist[y][x];
            for (int d = 0; d < 6; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (0 <= nx && nx < n && 0 <= ny && ny < n && dist[ny][nx] == -1) {
                    dist[ny][nx] = dist[y][x] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st.nextToken());
        int sx = Integer.parseInt(st.nextToken());
        int ty = Integer.parseInt(st.nextToken());
        int tx = Integer.parseInt(st.nextToken());
        System.out.println(bfs(sx, sy, tx, ty));
    }
}
