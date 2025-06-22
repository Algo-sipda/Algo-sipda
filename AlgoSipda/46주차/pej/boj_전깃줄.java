// [BOJ] 전깃줄
// https://www.acmicpc.net/problem/2565
// LIS 

import javax.swing.text.Style;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main { // 반도체 설계
    static int N;
    static int[][] port; // 왼쪽 포트, 오른쪽 포트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        port = new int[N ][2];
        for (int i = 0; i < N; i++) { // 1 ~ N
            st = new StringTokenizer(br.readLine());
            port[i][0] = Integer.parseInt(st.nextToken());
            port[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(port, Comparator.comparingInt(x -> x[0]));
        // LIS 최장 증가 수열
        int[] dp = new int[N]; // 최장 증가 부분수열의 길이 (i번째 수로 끝나는)
        for (int k = 0; k < N; k++) {
            dp[k] = 1;
            for (int i = 0; i < k; i++) {
                if (port[i][1] < port[k][1]) {
                    dp[k] = Math.max(dp[i] + 1, dp[k]);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println(N - res);
    }
}
