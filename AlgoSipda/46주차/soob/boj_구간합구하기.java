import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static long[] tree;
	static long[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N + 1];
		tree = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
			update(i, nums[i]);
		}

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 1 : 변경, 2 : 출력
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			switch (a) {
			case 1:
				update(b, c - nums[b]);
				nums[b] = c;
				break;
			case 2:
				System.out.println(sum(b, (int) c));
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 입력 값으로 tree를 구성하는 함수
	 */
	public static void update(int i, long num) {
		while (i <= N) {
			tree[i] += num;
			i += (i & -i); // 다음 위치에 데이터를 update		다음 update할 위치 => index + k
		}
	}

	/**
	 * start~end 까지의 구간 합 구하기
	 */
	public static long sum(int start, int end) {
		long ans = 0;
		int i = end;
		while (i > 0) {
			ans += tree[i];
			i -= (i & -i); // 이전 구간의 합이 있는 위치로 이동	이전 구간 합이 있는 위치 => index - k
		}

		i = start - 1;
		while (i > 0) {
			ans -= tree[i];
			i -= (i & -i);
		}
		return ans;
	}

}