import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_풍선맞추기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[1000001];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (dp[arr[i]] > 0) {
                dp[arr[i]] -= 1;
                dp[arr[i] - 1] += 1;
            } else {
                cnt++;
                dp[arr[i] - 1] += 1;
            }
        }

        System.out.println(cnt);
    }
}
