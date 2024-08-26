import java.io.*;
import java.util.*;

/*
    1 ~ M까지의 수 중 N개를 골라 -> 수열 생성
    다음 원소 >= 이전 원소 * 2
    => 출력: 수열의 경우의 수
    
    1~10 -> 4개 -> 4
    1 2 4 8
    1 2 4 9
    1 2 4 10
    1 2 5 10

    1~10 -> 3개 -> 7
    1 2 4
    1 2 5
    1 2 6
    1 2 7
    1 2 8
    1 2 9
    1 2 10


*/

public class Main {

    static int N, M;
    static int[][] dp;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N+1][M+1];
        for(int i=1; i<=M;i++) {
            dp[1][i] = i;
        }

        for(int i=2; i<=N;i++) {
            int start = (int)Math.pow(2, i-1);
            for(int j=start; j<=M;j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j/2])%MOD;
            }
        }

        System.out.println(dp[N][M]);
    }
}
