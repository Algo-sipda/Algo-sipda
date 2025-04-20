import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 입국 심사대 개수 N개 , 친구들 총 M명
	static int[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		long max = 0;
		time = new int[N];
		for (int n = 0; n < N; n++) {
			time[n] = Integer.parseInt(br.readLine());
			max = Math.max(max, time[n]);
		}
		Arrays.sort(time);

		long left = 0, right = max * M; // 가장 느린 심사관이 모든 인원을 다 맡았을 때 걸리는 최대 시간
		while (left <= right) {
			long mid = (right - left) / 2 + left;
			if (count(mid) >= M) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(left);
	}

	// 심사 시간이 주어졌을 때 각 심사대에서 x시간 동안 몇명을 처리할 지 계산할 수 있음
	static long count(long m) {
		long res = 0;
		for (int t : time) {
			res += m / t;
			if (res >= M) { // Long overflow 방지!!! 33%
				return M;
			}
		}
		return res;
	}

}
