import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class boj_미로만들기 {

    static int N, res;
    static int[][] map, dp;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = (int) line.charAt(j) - '0';
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        res = Integer.MAX_VALUE;
        bfs();

        System.out.println(res);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0, 0});
        dp[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N - 1 && cur[1] == N - 1) {
                res = Math.min(res, cur[2]);
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (isOut(nr, nc)) continue;
                if (map[nr][nc] == 1 && dp[nr][nc] > cur[2]) {
                    dp[nr][nc] = cur[2];
                    queue.add(new int[] {nr, nc, dp[nr][nc]});
                } else if (map[nr][nc] == 0 && dp[nr][nc] > cur[2] + 1) {
                    dp[nr][nc] = cur[2] + 1;
                    queue.add(new int[] {nr, nc, dp[nr][nc]});
                }
            }
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}

// 84%에서 메모리 초과