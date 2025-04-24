// 2624 동전바꿔주기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T; // 지폐의 금액
	static int K; // 동전의 가지수
	// 동전의 금액 pi와 개수 ni
	static int[] pi;
	static int[] ni;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		pi = new int[K]; // 동전 금액
		ni = new int[K]; // 동전의 가지수
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pi[k] = Integer.parseInt(st.nextToken());
			ni[k] = Integer.parseInt(st.nextToken());
		}

		// k번째 종류까지 고려했을 때 T원
		int[] dp = new int[T + 1];
		dp[0] = 1;

		for (int k = 0; k < K; k++) {// k 번째 동전을
			int[] next = new int[T + 1];
			for (int c = 0; c <= ni[k]; c++) { // c개 사용했을 때
				for (int t = 0; t <= T; t++) { // T원을 만들 수 있는지
					if (t - pi[k] * c >= 0) {
						next[t] += dp[t - pi[k] * c];
					}
				}
			}
			dp = next;
		}

		System.out.println(dp[T]);
	}

	// ni종류의 동전을 사용해서, 어떤 금액을 만들 수 있는가 dp[금액]
	static void back(int value, int id) {
		if (value == 0) {
			res += 1;
			return;
		}
		for (int c = 0; c <= pi[id]; c++) {
			if (value - pi[id] * c >= 0) {
				back(value - pi[id] * c, id + 1);
			}
		}
	}

}
