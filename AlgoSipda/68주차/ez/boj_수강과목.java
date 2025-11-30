import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_수강과목 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] classes = new int[K + 1];
        int[] time = new int[K + 1];
        int[][] dp  =new int[K + 1][N + 1];

        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            classes[i] = Integer.parseInt(st.nextToken());
            time[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < K + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (time[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - time[i]] + classes[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[K][N]);
    }
}
