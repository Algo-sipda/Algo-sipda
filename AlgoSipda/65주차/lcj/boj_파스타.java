import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 10_000;
	static int N, K, ans;
	static int[] arr;
	static Integer[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0;
		arr = new int[N + 1];
		Arrays.fill(arr, -1);
		dp = new Integer[N + 1][3][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken()) - 1;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				ans += recur(N, i, j);
			}
		}
		System.out.println(ans % MOD);
		br.close();
	}

	private static int recur(int cur, int sel, int cnt) {
		if (arr[cur] != -1 && arr[cur] != sel)
			return 0;
		if (cur == 1)
			return cnt == 0 ? 1 : 0;
		if (dp[cur][sel][cnt] != null)
			return dp[cur][sel][cnt];
		dp[cur][sel][cnt] = 0;
		int tmp = 0;
		if (cnt == 0) {
			for (int i = 0; i < 3; i++) {
				if (i == sel)
					continue;
				tmp = (tmp + recur(cur - 1, i, 0) + recur(cur - 1, i, 1)) % MOD;
			}
		} else
			tmp = recur(cur - 1, sel, 0);
		return dp[cur][sel][cnt] = tmp;
	}
}