// [BOJ] 진우의 달 여행(small) 
// https://www.acmicpc.net/problem/17484

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] box = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++){
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] dp = new int[N][M][3];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        for(int j = 0; j < M; j++){
            for(int d = 0; d < 3; d++) {
                dp[0][j][d] = box[0][j];  // 시작점: 방향 관계없이 시작 가능
            }
        }


        for(int i = 1; i < N; i++){
            for(int j = 0; j < M; j++){
                for(int d = 0; d < 3; d++) {
                    // dp[i][j][0] : (i, j)에 왼쪽 아래로 도착했을 때 최소 연료합   i-1, j+1 // 1, 2
                    // dp[i][j][1] : (i, j)에 바로 아래로 도착했을 때 최소 연료합   i-1,j  // 0, 2
                    // dp[i][j][2] : (i,j) i-1, j-1 // 0,1
                    if(j < M-1){
                        if(Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) != Integer.MAX_VALUE) { //overflow 방지
                            dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + box[i][j];
                        }
                    }
                    if(j > 0){
                        if(Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) != Integer.MAX_VALUE)
                            dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + box[i][j];
                    }

                    if(Math.min(dp[i-1][j][0], dp[i-1][j][2]) != Integer.MAX_VALUE)
                        dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + box[i][j];


                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int j = 0; j < M; j++){
            for(int d = 0; d < 3; d++){
                ans = Math.min(ans, dp[N-1][j][d]);
            }
        }
        System.out.println(ans);
    }
}

