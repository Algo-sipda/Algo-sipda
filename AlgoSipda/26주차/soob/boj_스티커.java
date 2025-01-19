import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n + 1];
            int[][] dp = new int[2][n + 1];

            for (int y = 0; y < 2; y++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 1; x <= n; x++) {
                    sticker[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];

            for (int x = 2; x <= n; x++) {
                dp[0][x] = Math.max(dp[1][x - 1], dp[1][x - 2]) + sticker[0][x];
                dp[1][x] = Math.max(dp[0][x - 1], dp[0][x - 2]) + sticker[1][x];
            }

            int answer = Math.max(dp[0][n], dp[1][n]);
            System.out.println(answer);
        }
    }
}