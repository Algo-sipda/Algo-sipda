import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // 지폐의 금액
        int K = Integer.parseInt(br.readLine());    // 동전의 가지 수

        int[] coin = new int[K + 1];
        int[] count = new int[K + 1];

        for (int i = 1; i <= K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coin[i] = Integer.parseInt(st.nextToken());
            count[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[K + 1][T + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= T; j++) {
                for (int k = 0; k <= count[i]; k++) {
                    int prev = j - coin[i] * k;
                    if (prev < 0) break;
                    dp[i][j] += dp[i - 1][prev];
                }
            }
        }

        System.out.println(dp[K][T]);
    }
}
