// [BOJ] 세용액 https://www.acmicpc.net/problem/2473

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);

		long minSum = Long.MAX_VALUE;
		long ans1 = 0, ans2 = 0, ans3 = 0;
		for (int a = 0; a < N - 2; a++) { // 제일 작은 수 고정
			int left = a + 1;
			int right = N - 1;

			while (left < right) { // 나머지 두 수 구하기
				long sum = arr[a] + arr[left] + arr[right];
				if (Math.abs(sum) < minSum) { // 0으로부터 가깝게 존재한다면,
					minSum = Math.abs(sum);
					ans1 = arr[a];
					ans2 = arr[left];
					ans3 = arr[right];
				}
				if (sum < 0) { // 음수 -> 양수화 시켜야함
					left += 1;
				} else {
					right -= 1;
				}
			}
		}
		System.out.println(ans1 + " " + ans2 + " " + ans3);

	}
}

