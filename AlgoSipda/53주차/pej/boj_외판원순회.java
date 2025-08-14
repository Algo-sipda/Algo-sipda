// [BOJ] 외판원 순회
// https://www.acmicpc.net/problem/2098

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; // 도시의 수
	static int[][] W; // 비용 행렬
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

		W = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dp[current][visited] // current까지 왔고 visitied 상태 일떄 최소 비용
		dp = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		int res = tsp(0, 1);
		System.out.println(res);
	}

	static int tsp(int current, int visited) {
		if (visited == (1 << N) - 1) { // 모든 장소 다 방문
			if (W[current][0] == 0) {
				return Integer.MAX_VALUE;
			}
			return W[current][0];
		}
		if (dp[current][visited] != -1)
			return dp[current][visited];
		int min = Integer.MAX_VALUE;
		for (int next = 0; next < N; next++) {
			if ((visited & (1 << next)) != 0 || W[current][next] == 0)
				continue;
			int temp = tsp(next, visited | (1 << next));
			if (temp != Integer.MAX_VALUE) {
				int cost = temp + W[current][next];
				min = Math.min(min, cost);
			}
		}
		dp[current][visited] = min;
		return min;
	}

}
