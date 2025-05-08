// [BOJ] 사탕 가게 https://www.acmicpc.net/problem/4781
// float 처리 : int형으로 바꾸는데 0.5f 더해서 바꾸기 
// DP 배낭 문제 : n번째 사탕까지 고려했을 때 m원으로 얻을 수 있는 최대칼로리 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; // 가게에 있는 사탕 종류의 수
	static int M; // 상근이가 가지고 있는 돈의 양
	static Candy[] candies;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = (int) (100 * Float.parseFloat(st.nextToken()) + 0.5f);
			if (N == 0 && M == 0) {
				break;
			}
			candies = new Candy[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int calory = Integer.parseInt(st.nextToken());
				int price = (int) (100 * Float.parseFloat(st.nextToken()) + 0.5f); // float 처리
				candies[i] = new Candy(calory, price);
			}
			// dp 배낭 문제
			int[][] dp = new int[N + 1][M + 1]; // n번째 사탕까지 고려했을 때 m원으로 얻을 수 있는 최대칼로리

			for (int i = 1; i <= N; i++) {
				for (int p = 0; p <= M; p++) {
					// 이전까지의 최대 칼로리를 먼저 그대로 가져옴
					dp[i][p] = dp[i - 1][p];

					// 현재 사탕을 살 수 있으면 갱신
					if (p >= candies[i - 1].amount) {
						dp[i][p] = Math.max(dp[i][p], dp[i][p - candies[i - 1].amount] + candies[i - 1].calory);
					}
				}
			}

			System.out.println(dp[N][M]);
		}
	}

	static class Candy {
		int calory;
		int amount;

		Candy(int calory, int amount) {
			this.calory = calory;
			this.amount = amount;
		}
	}
}
