import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] nArr = new int[T];
        int[] mArr = new int[T];
        int maxN = 0;
        int maxM = 0;

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            nArr[t] = n;
            mArr[t] = m;
            if (maxN < n) maxN = n;
            if (maxM < m) maxM = m;
        }

        long[][] dp = new long[maxN + 1][maxM + 1];

        for (int j = 0; j <= maxM; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= maxN; i++) {
            for (int j = 1; j <= maxM; j++) {
                dp[i][j] = dp[i][j - 1];
                dp[i][j] += dp[i - 1][j / 2];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            sb.append(dp[nArr[t]][mArr[t]]).append('\n');
        }
        System.out.print(sb);
    }
}
