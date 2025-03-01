import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] cities = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cities[i][0] = Integer.parseInt(st.nextToken());
            cities[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][C + 1];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                if (cities[i - 1][1] >= j) {
                    dp[i][j] = Math.min(dp[i - 1][j], cities[i - 1][0]);
                } else {
                    dp[i][j] =
                            Math.min(dp[i - 1][j], dp[i][j - cities[i - 1][1]] + cities[i - 1][0]);
                }
            }
        }

        System.out.println(dp[N][C]);
    }
}
