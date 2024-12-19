import java.util.*;
import java.io.*;


public class Main {

    public static int n, m;
    public static int[] nums;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        dp = new int[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i - 1], -1);
            nums[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            out.append(palindrome(s, e)).append("\n");
        }

        System.out.println(out);
    }

    public static int palindrome(int s, int e) {
        if(dp[s][e] != -1) return dp[s][e];

        if(nums[s] != nums[e]) return dp[s][e] = 0;

        if(e - s <= 2) return dp[s][e] = (nums[e] == nums[s]) ? 1 : 0;

        return dp[s][e] = palindrome(s + 1, e - 1);
    }

}
