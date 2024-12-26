import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_COST = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memories = new int[N];
        int[] costs = new int[N];
        int[][] dp = new int[MAX_COST + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = (Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i <= MAX_COST; i++) {
            for (int j = 1; j <= N; j++) {
                int app = j - 1;
                if (i < costs[app]) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - costs[app]][j - 1] + memories[app]);
                if (dp[i][j] >= M) {
                    System.out.println(i);
                    return;
                }
            }
        }
    }
}

// 배낭 문제
