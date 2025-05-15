import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_사탕가게 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken().replace(".", ""));
            if (N == 0 && M == 0) {
                break;
            }

            int[] dp = new int[M + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int cal = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken().replace(".", ""));
                for (int j = cost; j <= M; j++) {
                    dp[j] = Math.max(dp[j], dp[j - cost] + cal);
                }
            }
            System.out.println(dp[M]);
        }
    }
}