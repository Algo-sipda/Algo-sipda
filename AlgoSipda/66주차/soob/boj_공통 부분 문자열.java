import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                char c2 = s2.charAt(j - 1);
                if (c1 == c2)
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                if (answer < dp[i][j])
                    answer = dp[i][j];
            }
        }

        System.out.println(answer);
    }
}
