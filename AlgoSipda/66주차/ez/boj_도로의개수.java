import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_도로의개수 {

    static int N, M, K;
    static long[][] dp;
    static int[][] hori, verti;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        dp = new long[N + 1][M + 1];
        hori = new int[N][M + 1];
        verti = new int[N + 1][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (b == d) {
                hori[Math.min(a, c)][b] = 1;
            } else {
                verti[a][Math.min(b, d)] = 1;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (hori[i - 1][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 1; i < M + 1; i++) {
            if (verti[0][i - 1] == 1) break;
            dp[0][i] = 1;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];

                if (hori[i - 1][j] == 1) {
                    dp[i][j] -= dp[i - 1][j];
                }

                if (verti[i][j - 1] == 1) {
                    dp[i][j] -= dp[i][j - 1];
                }
            }
        }
        System.out.println(dp[N][M]);
    }
}
