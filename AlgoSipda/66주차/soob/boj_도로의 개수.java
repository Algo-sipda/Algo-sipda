import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        boolean[][] blockRight = new boolean[m + 1][n];
        boolean[][] blockUp = new boolean[m][n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            if (y1 == y2) {
                int y = y1;
                int x = Math.min(x1, x2);
                blockRight[y][x] = true;
            } else {
                int x = x1;
                int y = Math.min(y1, y2);
                blockUp[y][x] = true;
            }
        }
        long[][] dp = new long[m + 1][n + 1];
        dp[0][0] = 1L;
        for (int y = 0; y <= m; y++) {
            for (int x = 0; x <= n; x++) {
                if (x < n && !blockRight[y][x])
                    dp[y][x + 1] += dp[y][x];
                if (y < m && !blockUp[y][x])
                    dp[y + 1][x] += dp[y][x];
            }
        }
        System.out.println(dp[m][n]);
    }
}
