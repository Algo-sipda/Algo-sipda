import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_극장좌석 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < N + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int ans = 1;
        int before = 0;
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            ans *= dp[vip -  before - 1];
            before = vip;
        }
        ans *= dp[N - before];

        System.out.println(ans);
    }
}
