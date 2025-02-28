import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_호텔 {

    static int C, N;
    static int[] dp;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[C + 101];
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int cus = Integer.parseInt(st.nextToken());
            arr[i][0] = cost;
            arr[i][1] = cus;
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int cost = arr[i][0];
            int cus = arr[i][1];
            for (int j = cus; j < dp.length; j++) {
                if (dp[j - cus] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j - cus] + cost, dp[j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = C; i < dp.length; i++) {
            min = Math.min(min, dp[i]);
        }

        System.out.println(min);
    }
}
