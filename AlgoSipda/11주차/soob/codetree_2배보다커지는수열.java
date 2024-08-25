import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][1] = 1;
            for (int j = 1; j < n; j++) {
                for (int k = i * 2; k <= m; k++) {
                    dp[k][j + 1] = (dp[k][j + 1] + dp[i][j]) % 1000000007;
                }
            }
        }

        long sum = 0;
        for (int[] arr : dp) {
            sum += arr[n];
        }

        System.out.println(sum % 1000000007);
    }
}