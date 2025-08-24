// [BOJ] LCS
// https://www.acmicpc.net/problem/9251

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();

		N = str1.length();
		M = str2.length();
		// dp[i][j] = case(같은 경우, 다른 경우)
		// 1) 같은 경우 : dp[i-1][j-1] + 1
		// 2) 다른 경우 : Math.max(dp[i-1][j] , dp[i][j-1])
		dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		System.out.println(dp[N][M]);
	}

}