import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, R, G, B;
    static long[][][][] dp;
    static long[][] comb = new long[121][121];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int totalBalls = N * (N + 1) / 2;
        if (R + G + B < totalBalls) {
            System.out.println(0);
            return;
        }

        for (int n = 0; n <= 120; n++) {
            comb[n][0] = comb[n][n] = 1;
            for (int r = 1; r < n; r++)
                comb[n][r] = comb[n - 1][r - 1] + comb[n - 1][r];
        }

        dp = new long[R + 1][G + 1][B + 1][N + 1];
        dp[R][G][B][0] = 1;

        for (int k = 0; k < N; k++) {
            int layer = k + 1;
            for (int r = 0; r <= R; r++) {
                for (int g = 0; g <= G; g++) {
                    for (int b = 0; b <= B; b++) {
                        long cur = dp[r][g][b][k];
                        if (cur == 0) continue;

                        // 1색
                        if (r >= layer)
                            dp[r - layer][g][b][k + 1] += cur;
                        if (g >= layer)
                            dp[r][g - layer][b][k + 1] += cur;
                        if (b >= layer)
                            dp[r][g][b - layer][k + 1] += cur;

                        // 2색
                        if (layer % 2 == 0) {
                            int half = layer / 2;
                            long c = comb[layer][half];
                            if (r >= half && g >= half)
                                dp[r - half][g - half][b][k + 1] += c * cur;
                            if (r >= half && b >= half)
                                dp[r - half][g][b - half][k + 1] += c * cur;
                            if (g >= half && b >= half)
                                dp[r][g - half][b - half][k + 1] += c * cur;
                        }

                        // 3색
                        if (layer % 3 == 0) {
                            int third = layer / 3;
                            if (r >= third && g >= third && b >= third) {
                                long c = comb[layer][third] * comb[layer - third][third];
                                dp[r - third][g - third][b - third][k + 1] += c * cur;
                            }
                        }
                    }
                }
            }
        }

        long answer = 0;
        for (int r = 0; r <= R; r++)
            for (int g = 0; g <= G; g++)
                for (int b = 0; b <= B; b++)
                    answer += dp[r][g][b][N];

        System.out.println(answer);
    }
}
