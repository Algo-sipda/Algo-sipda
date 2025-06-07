// [BOJ] 극장좌석 https://www.acmicpc.net/problem/2302
// 고정 좌석 별로 나눈다 

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main { // 79퍼 시간 복잡도
	static int N, M;
	static int cnt;
	static boolean[] isStatic;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isStatic = new boolean[N + 1];

		M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			int x = Integer.parseInt(br.readLine());
			isStatic[x] = true;

		}
        if(N == 1) {
            System.out.println(1); 
            return;
        }
        int[] dp = new int[N+1]; // 1 - N까지의 좌석이 있을 때 앉을 수 있는 경우의 수 
        dp[1] = 1; 
        dp[2] = 2; // 바꾸는 경우 
        // 3 :  1 2 3 / 1 3 2 / 2 1 3  => 3가지 
        // 4 :  1 2 3 4 / 2 1 3 4 / 1 3 2 4 / 1 2 4 3 / 2 1 4 3 => 5가지 
        // dp[n] = dp[n-1] + dp[n-2];
        for(int i = 3; i <= N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        int answer = 1;
        int partCnt = 0;
        for(int i = 1; i <= N; i++){
            if(isStatic[i]) {
                if(partCnt == 0)continue;
                else {
                    answer *= dp[partCnt];
                    partCnt = 0;
                }
            } else {
                partCnt += 1;
            }
        }

        if(partCnt != 0){
            answer *= dp[partCnt];
        }

        System.out.println(answer); 
    }
}