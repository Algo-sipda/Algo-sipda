import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());   // 목표 고객 수
        int n = Integer.parseInt(st.nextToken());   // 도시 개수
        int[] dp = new int[c + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            if (i == 0) {
                for (int j = 1; j <= c; j++) {
                    int needCost = (j / customer) * cost;
                    if (j % customer != 0)
                        needCost += cost;
                    dp[j] = needCost;
                }
            } else {
                for (int j = 1; j <= c; j++) {
                    if (j < customer) {
                        int needCost = (j / customer) * cost;
                        if (j % customer != 0)
                            needCost += cost;
                        dp[j] = Math.min(dp[j], needCost);
                    } else
                        dp[j] = Math.min(dp[j - customer] + cost, dp[j]);
                }
            }
        }

        System.out.println(dp[c]);
    }
}