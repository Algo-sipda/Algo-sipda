import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_소형기관차 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] train = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            if (i > 1) {
                train[i] += train[i - 1];
            }
        }
        int max = Integer.parseInt(br.readLine());
        int[][] dp = new int[4][N + 1];

        for (int i = 1; i < 4; i++) {
            for (int j = i * max; j < N + 1; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - max] + train[j] - train[j - max]);
            }
        }

        System.out.println(dp[3][N]);
    }
}