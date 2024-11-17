import java.util.*;
import java.io.*;

public class boj_평범한배낭 {
    static class Item {
        int weight, value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        Item[] items = new Item[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int W = Integer.parseInt(st.nextToken());
            int V =  Integer.parseInt(st.nextToken());
            items[i] = new Item(W, V);
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i].weight] + items[i].value);
            }
        }

        System.out.println(dp[N][K]);
    }
}