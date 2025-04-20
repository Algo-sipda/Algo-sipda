import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[T + 1][K + 1];
        Arrays.fill(dp[0], 1);

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int coin = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= cnt; j++) {
                int price = coin * j;

                for (int k = price; k <= T; k++) {
                    dp[k][i + 1] += dp[k - price][i];
                }
            }

            for (int j = 1; j <= T; j++) {
                dp[j][i + 1] += dp[j][i];
            }
        }

        System.out.println(dp[T][K]);
    }
}
