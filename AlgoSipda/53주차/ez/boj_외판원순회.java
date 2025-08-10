import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_외판원순회 {

    static int N;
    static int[][] map, dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][(1 << N)];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(backtracking(0, 1));
    }

    private static int backtracking(int cur, int visited) {
        if (visited == (1 << N) - 1) {
            if (map[cur][0] == 0) {
                return INF;
            }
            return map[cur][0];
        }

        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }

        dp[cur][visited] = INF;

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 0 && map[cur][i] != 0) {
                dp[cur][visited] = Math.min(dp[cur][visited], backtracking(i, visited | (1 << i)) + map[cur][i]);
            }
        }
        return dp[cur][visited];
    }
}
