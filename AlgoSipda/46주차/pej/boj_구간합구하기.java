// 2042 구간 합 구하기
// https://www.acmicpc.net/problem/2042

import java.io.BufferedReader;// 세그먼트 트리
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr;
	static long[] segment;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수의 개수 <= 1_000_000
		M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수 <= 10_000
		K = Integer.parseInt(st.nextToken()); // 구간합을 구하는 횟수 <= 10_000

		arr = new long[N + 1];
		for(int n = 1; n < N + 1; n++) {
			arr[n] = Long.parseLong(br.readLine());
		}

		init(arr);

		for(int x = 0; x < M + K; x++) {
			// a = 1, arr[b] = c
			// a = 2, sum(b, c)
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				update(b, c, arr);
			} else {
				System.out.println(query(b, (int)c));
			}
		}
	}

	public static void init(long[] arr) {
		long height = (int) Math.ceil(Math.log(N)/ Math.log(2));
		int size = 1 << (height + 1);
		segment = new long[size];
		build(arr, 1, 1, N);
	}

	public static long build(long[] arr, int node, int start, int end) {
		if(start == end) {
			return segment[node] = arr[start];
		}
		int mid = (start + end) / 2;
		return segment[node] = build(arr, node * 2, start, mid) + build(arr, node * 2 + 1, mid + 1, end);
	}

	public static void update(int i, long value, long[] arr){
		long diff = value - arr[i];
		arr[i] = value;
		update(1, 1, N, i, diff); // node, start, end, index, diff
	}

	private static void update(int node, int start, int end, int index, long diff) {
		if(start > index || end < index) return;
		segment[node] += diff;
		if(start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, index, diff);
			update(node * 2 + 1, mid + 1, end, index, diff);
		}

	}

	public static long query(int l, int r) {
		// node, start, end, left, right
		return query(1, 1, N, l, r);
	}

	// l - r 구간 합
	private static long query(int node, int start, int end, int left, int right) {
		if(right < start || end < left) return 0;
		if(start >= left && end <= right) {
			return segment[node];
		}
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
}