import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_기타리스트 {

    static int N, S, M, ans;
    static int[] V;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N+1][M+1];
        dp[0][S] = true;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                if (dp[i - 1][j]) {
                    if (j + V[i - 1] <= M) {
                        dp[i][j + V[i - 1]] = true;
                    }
                    if (j - V[i - 1] >= 0) {
                        dp[i][j - V[i - 1]] = true;
                    }
                }
            }
        }

        ans = -1;
        for (int i = 0; i < M + 1; i++) {
            if (!dp[N][i]) continue;
            ans = Math.max(ans, i);
        }
        System.out.println(ans);
    }
}
