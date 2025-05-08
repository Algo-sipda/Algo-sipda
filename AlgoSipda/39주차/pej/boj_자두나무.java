// [BOJ] 자두나무 https://www.acmicpc.net/problem/2240

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { 
    static int T, W;
    static int[] fruit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken()); // t초 동안 떨어짐
        W = Integer.parseInt(st.nextToken()); // 최대 w번 움직임

        fruit = new int[T+1];        // t초에 fruit[t]에서 열매 떨어짐 자두가 받을 수 있는 자두의 개수
        for (int t = 1; t <= T; t++) {
            fruit[t] = Integer.parseInt(br.readLine());
        }
        // dp : t초까지 고려했을 때 자두가 받을 수 있는 자두의 개수
        // w : 움직인 횟수
        // 1번에 자두 떨어지는 상황
        // dP[t][1][w] = Math.max(dp[t-1][1][w] + 1 , dp[t-1][2][w-1] +1 )
        // dp[t][2][w] = Math.max(dp[t-1][1][w-1] , dp[t-1][2][w] )
        int[][][] dp = new int[T + 1][2 + 1][W + 2]; // t>=1 && t<=T, 1또는2, w>=0 && w<=W+1

        for (int t = 1; t <= T; t++) {
            for (int w = 1; w <= W + 1; w++) {
                if (fruit[t] == 1) { // 자두가 1에서 떨어지는 상황
                    dp[t][1][w] = Math.max(dp[t-1][1][w] + 1 , dp[t-1][2][w-1] + 1 );
                    dp[t][2][w] = Math.max(dp[t-1][1][w-1] , dp[t-1][2][w] );
                } else { // 자두가 2에서 떨어지는 상황
                    if(t==1 && w==1)continue;
                    dp[t][1][w] = Math.max(dp[t-1][1][w] , dp[t-1][2][w-1] );
                    dp[t][2][w] = Math.max(dp[t-1][1][w-1] + 1 , dp[t-1][2][w] + 1 );
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= W + 1; i++) {
            result = Math.max(result, Math.max(dp[T][1][i], dp[T][2][i]));
        }
        System.out.println(result);
    }
}
