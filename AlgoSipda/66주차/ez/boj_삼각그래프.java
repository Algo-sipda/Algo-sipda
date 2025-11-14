import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_삼각그래프 {

    static int N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int cnt = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            map = new int[N][3];
            dp = new int[N][3];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = 1000001;
                }
            }

            dp[0][0] = Integer.MAX_VALUE;
            dp[0][1] = map[0][1];
            dp[0][2] = Math.min(map[0][1] + map[0][2], dp[0][1] + map[0][2]);

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][0];
                dp[i][1] = Math.min(dp[i][0], Math.min(dp[i - 1][2], Math.min(dp[i - 1][0], dp[i - 1][1]))) + map[i][1];
                dp[i][2] = Math.min(dp[i][1], Math.min(dp[i - 1][1], dp[i - 1][2])) + map[i][2];
            }

            sb.append(cnt + ". " + dp[N - 1][1] + "\n");
            cnt++;
        }

        System.out.println(sb);
    }
}
