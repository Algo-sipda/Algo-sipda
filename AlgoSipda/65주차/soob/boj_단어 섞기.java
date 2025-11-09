import java.io.*;
import java.util.*;

public class Main {
    static String s1;
    static String s2;
    static String s3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            s1 = st.nextToken();
            s2 = st.nextToken();
            s3 = st.nextToken();
            sb.append("Data set ").append(t).append(": ");
            if (check()) 
                sb.append("yes\n");
            else 
                sb.append("no\n");
        }
        System.out.print(sb.toString());
    }

    static boolean check() {
        if (s1.length() + s2.length() != s3.length()) 
            return false;
        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j <= m; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1)) {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char target = s3.charAt(i + j - 1);
                boolean fromA = s1.charAt(i - 1) == target && dp[i - 1][j];
                boolean fromB = s2.charAt(j - 1) == target && dp[i][j - 1];
                dp[i][j] = fromA || fromB;
            }
        }
        return dp[n][m];
    }
}
