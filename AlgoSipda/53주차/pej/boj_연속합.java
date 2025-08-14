// [BOJ] 연속합
// https://www.acmicpc.net/problem/1912
// 새로이 시작하는게 나은가 아니면 아에 버려버리는게 나은가 

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int m = 0; m < N ; m++){
            arr[m] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[N];
        dp[0] = arr[0];
        for(int i = 1; i < N; i++){
            dp[i] = Math.max(arr[i] + dp[i-1], arr[i]); // 이전까지의 합이 나은지 아니면 싹 버려버리는게 나은지
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }

}
