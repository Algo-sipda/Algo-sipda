import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder out = new StringBuilder();

    static Integer[][] dp;
    static int[] weights;
    static int[] values;


    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        dp = new Integer[n][k + 1];
        weights = new int[n];
        values = new int[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapsack(n - 1, k));
    }

    public static int knapsack(int i, int k) {
        if(i < 0) return 0;

        if(dp[i][k] == null) {
            if(weights[i] > k) dp[i][k] = knapsack(i - 1, k);
            else dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - weights[i]) + values[i]);
        }

        return dp[i][k];
    }
}
