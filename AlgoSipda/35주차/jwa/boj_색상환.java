import java.io.*;

public class Main {
    static int MOD = 1_000_000_003;
    static int N, K, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][K + 1]; // i개 중 j개 고르는 경우의 수

        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
        }

        for (int i = 3; i < N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD; // 첫번째를 선택하거나 안 하거나
            }
        }

        for (int j = 2; j <= K; j++) {
            dp[N][j] = dp[N - 3][j - 1] + dp[N - 1][j] % MOD;
        }

        System.out.println(dp[N][K] % MOD);
    }
}
