import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1058_친구 {

	static int[][] cost = new int[51][51];
	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				cost[i][j] = str[j - 1] == 'Y' ? 1 : INF;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (j == k) continue;
					cost[j][k] = Math.min(cost[j][k], cost[j][i] + cost[i][k]);
				}
			}
		}

		int max = 0;

		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (cost[i][j] == 2 || cost[i][j] == 1) {
					cnt++;
				}
			}
			max = Math.max(max, cnt);
		}

		System.out.println(max);
	}
}