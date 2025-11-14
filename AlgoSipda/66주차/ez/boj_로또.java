import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_로또 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            long[][] dp = new long[N+1][M+1];
            dp[1][1] = 1;

            Arrays.fill(dp[0], 1);

            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < M + 1; j++) {
                    dp[i][j] = dp[i-1][j/2] + dp[i][j-1];
                }
            }

            sb.append(dp[N][M] + "\n");
        }
        System.out.println(sb.toString());
    }
}