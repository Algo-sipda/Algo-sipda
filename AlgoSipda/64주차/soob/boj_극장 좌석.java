import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 좌석의 개수
        int M = Integer.parseInt(br.readLine());    // 고정석의 개수
        boolean[] fixedSeat = new boolean[N+1];
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int m = 0; m < M; m++) {
            int no = Integer.parseInt(br.readLine());
            fixedSeat[no] = true;
        }

        for (int i = 2; i <= N; i++) {
            if(!fixedSeat[i-1] && !fixedSeat[i]){
                dp[i] = dp[i-2] + dp[i-1];
                continue;
            }
            dp[i] = dp[i-1];
        }

        System.out.println(dp[N]);
    }
}
