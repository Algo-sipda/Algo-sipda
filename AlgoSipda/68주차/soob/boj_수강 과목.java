import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 최대 공부시간
        int K = Integer.parseInt(st.nextToken());   // 과목 수
        int[][] arr = new int[K][2];
        int[] dp = new int[N + 1];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());   // 중요도
            int T = Integer.parseInt(st.nextToken());   // 공부시간
            arr[k][0] = I;
            arr[k][1] = T;
        }


        for (int k = 0; k < K; k++) {
            for (int i = N; i >= arr[k][1]; i--) {
                dp[i] = Math.max(dp[i], dp[i - arr[k][1]] + arr[k][0]);
            }
        }

        System.out.println(dp[N]);
    }
}
