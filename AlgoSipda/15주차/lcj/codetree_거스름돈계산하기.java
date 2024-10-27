import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static int[] V, A, dp;

    static final int INF = 1000001;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        V = new int[N];
        A = new int[N];
        int leastCoinCnt = 0;
        dp = new int[S+1];
        Arrays.fill(dp, INF);

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            V[i] = Integer.parseInt(st.nextToken());
            A[i] = Integer.parseInt(st.nextToken());
            leastCoinCnt += A[i];
            S -= (V[i]*A[i]);
        }

        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = V[i]; j <= S; j++) {
                if (dp[j - V[i]] != INF) {
                    dp[j] = Math.min(dp[j], dp[j - V[i]] + 1);
                }
            }
        }
        
        if (dp[S] == INF) {
            System.out.println(-1);  // 해당 금액을 만들 수 없는 경우
        } else {
            System.out.println(leastCoinCnt + dp[S]);  // 최소 동전 개수 출력
        }
    }
}