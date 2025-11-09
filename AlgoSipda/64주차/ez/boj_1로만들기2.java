import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1로만들기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }

        sb.append(dp[N]).append("\n");

        while (N > 0) {
            sb.append(N).append(" ");
            if (N == 1) break;
            if (N % 3 == 0 && dp[N / 3] == dp[N] - 1) {
                N /= 3;
            } else if (N % 2 == 0 && dp[N / 2] == dp[N] - 1) {
                N /= 2;
            } else {
                N -= 1;
            }
        }

        System.out.println(sb.toString().trim());
    }
}
