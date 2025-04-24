import java.io.*;
import java.util.*;

public class boj_동전바꿔주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[T + 1][K + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            dp[0][i] = 1;

            for (int j = 1; j <= cnt; j++) {
                int price = value * j;

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